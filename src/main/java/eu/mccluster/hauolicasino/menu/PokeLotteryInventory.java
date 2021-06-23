package eu.mccluster.hauolicasino.menu;

import ca.landonjw.gooeylibs2.api.UIManager;
import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.Template;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryLangConfig;
import eu.mccluster.hauolicasino.config.pokelottery.Data;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryConfig;
import eu.mccluster.hauolicasino.objects.LotteryObject;
import eu.mccluster.hauolicasino.utils.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PokeLotteryInventory {

    public static Page createPage(EntityPlayerMP playerMP) {

        PokeLotteryLangConfig _lang = HauoliCasino.getLang();
        Data _data = HauoliCasino.getData();
        PokeLotteryConfig _config = HauoliCasino.getConfig();
        LotteryObject _object = HauoliCasino._currentLottery.get(0);

        short pokemonIndexNumber = _object.getIndexNumber();
        Pokemon pokemon = GenLotteryPokemon.genPokemon(pokemonIndexNumber);
        EnumGrowth size = _object.getGrowth();
        EnumNature nature = _object.getNature();
        Gender gender = _object.getGender();
        AbilityBase ability = _object.getAbility().get();
        StatsType stats = _object.getStat();

        AtomicBoolean claimable = new AtomicBoolean(true);

        ItemStack claimItem;
        ItemStack sizeItem;
        ItemStack natureItem;
        ItemStack genderItem;
        ItemStack abilityItem;
        ItemStack statItem;

        List<String> loreClaim;
        List<String> loreSize = new ArrayList<>();
        List<String> loreNature = new ArrayList<>();
        List<String> loreGender = new ArrayList<>();
        List<String> loreAbility = new ArrayList<>();
        List<String> loreStat = new ArrayList<>();
        List<String> loreTime = new ArrayList<>();

        loreTime.add(Placeholder.remainingTime(_lang.remainingTime));

        List<Boolean> checks = LotteryUtils.checkPokemon(playerMP, pokemon, size, nature, gender, ability, stats);

        int equals = LotteryUtils.equalSize(checks);

        if(_data.playerList.contains(playerMP.getUniqueID().toString())) {
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimable, 1);
            claimable.set(false);
            loreClaim = _lang.alreadyClaimed;
        } else if(claimable.get() && !LotteryUtils.hasLotteryPokemon(playerMP, pokemon)) {
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimable, 1);
            claimable.set(false);
            loreClaim = _lang.noLotteryPokemon;
        }  else if(claimable.get() && equals == 0) {
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimable, 1);
            claimable.set(false);
            loreClaim = _lang.noIdentifiers;
        } else if(claimable.get() && _config.originalTrainer ) {
            claimable.set(LotteryUtils.isOriginalTrainer(playerMP, pokemon));
            if (claimable.get()) {
                claimItem = LootUtils.itemStackFromType(_config.inventorySettings.claimItem, 1);
                loreClaim = _lang.canClaim;
            } else {
                claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimable, 1);
                loreClaim = _lang.notOriginalTrainer;
            }
        } else {
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.claimItem, 1);
            claimable.set(true);
            loreClaim = _lang.canClaim;
        }



        if(!checks.get(0)) {
            sizeItem = LootUtils.itemStackFromType(_config.inventorySettings.growthNotFulfilled, 1);
            loreSize.add(_lang.requirementFalse);
        } else {
            sizeItem = LootUtils.itemStackFromType(_config.inventorySettings.growthFulfilled, 1);
            loreSize.add(_lang.requirmentTrue);
        }
        loreSize.addAll(_lang.reqGrowth);



        if(!checks.get(1)) {
            natureItem = LootUtils.itemStackFromType(_config.inventorySettings.natureNotFulfilled, 1);
            loreNature.add(_lang.requirementFalse);
        } else {
            natureItem = LootUtils.itemStackFromType(_config.inventorySettings.natureFulfilled, 1);
            loreNature.add(_lang.requirmentTrue);
        }
        loreNature.addAll(_lang.reqNature);



        if(!checks.get(2)) {
            genderItem = LootUtils.itemStackFromType(_config.inventorySettings.genderNotFulfilled, 1);
            loreGender.add(_lang.requirementFalse);
        } else {
            genderItem = LootUtils.itemStackFromType(_config.inventorySettings.genderFulfilled, 1);
            loreGender.add(_lang.requirmentTrue);
        }
        loreGender.addAll(_lang.reqGender);



        if(!checks.get(3)) {
            abilityItem = LootUtils.itemStackFromType(_config.inventorySettings.abilityNotFulfilled, 1);
            loreAbility.add(_lang.requirementFalse);
        } else {
            abilityItem = LootUtils.itemStackFromType(_config.inventorySettings.abilityFulfilled, 1);
            loreAbility.add(_lang.requirmentTrue);
        }
        loreAbility.addAll(_lang.reqAbility);



        if(!checks.get(4)) {
            statItem = LootUtils.itemStackFromType(_config.inventorySettings.ivNotFulfilled, 1);
            loreStat.add(_lang.requirementFalse);
        } else {
            statItem = LootUtils.itemStackFromType(_config.inventorySettings.ivFulfilled, 1);
            loreStat.add(_lang.requirmentTrue);
        }
        loreStat.addAll(_lang.reqStat);



        Button outerPanes = GooeyButton.builder()
                .display(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, _config.inventorySettings.outsideGlassPaneColor))
                .title(TextUtils.regex(_lang.glassPanes))
                .build();

        Button innerPanes = GooeyButton.builder()
                .display(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, _config.inventorySettings.innerGlassPaneColor))
                .title(TextUtils.regex(_lang.glassPanes))
                .build();

        Button lotterypokemon = GooeyButton.builder()
                .display(GenLotteryPokemon.genPokemonSprite(pokemonIndexNumber))
                .title(TextUtils.regex(_lang.colorPokemon + GenLotteryPokemon.genPokemonName(pokemonIndexNumber)))
                .build();

        Button claimBase = GooeyButton.builder()
                .display(claimItem)
                .title(TextUtils.regex(_lang.titleClaim))
                .lore(TextUtils.regexList(Placeholder.lootAmountList(loreClaim, equals)))
                .onClick((buttonAction -> {
                    if(claimable.get()) {
                        EntityPlayerMP actionPlayer = buttonAction.getPlayer();
                        LootUtils.genLoot(actionPlayer, equals);
                        HauoliCasino.getData().playerList.add(playerMP.getUniqueID().toString());
                        HauoliCasino.getData().save();
                        UIManager.closeUI(actionPlayer);
                    }
                }))
                .build();

        Button time = GooeyButton.builder()
                .display(LootUtils.itemStackFromType(_config.inventorySettings.timeItem, 1))
                .title(TextUtils.regex(_lang.titleTime))
                .lore(loreTime)
                .build();

        Button requirementSize = GooeyButton.builder()
                .display(sizeItem)
                .title(TextUtils.regex(_lang.titleGrowth))
                .lore(TextUtils.regexList(Placeholder.currentGrowth(loreSize)))
                .build();

        Button requirementNature = GooeyButton.builder()
                .display(natureItem)
                .title(TextUtils.regex(_lang.titleNature))
                .lore(TextUtils.regexList(Placeholder.currentNature(loreNature)))
                .build();

        Button requirementGender = GooeyButton.builder()
                .display(genderItem)
                .title(TextUtils.regex(_lang.titleGender))
                .lore(TextUtils.regexList(Placeholder.currentGender(loreGender)))
                .build();

        Button requirementAbility = GooeyButton.builder()
                .display(abilityItem)
                .title(TextUtils.regex(_lang.titleAbility))
                .lore(TextUtils.regexList(Placeholder.currentAbility(loreAbility)))
                .build();

        Button requirementStat = GooeyButton.builder()
                .display(statItem)
                .title(TextUtils.regex(_lang.titleIV))
                .lore(TextUtils.regexList(Placeholder.currentStat(loreStat)))
                .build();

        Template template = ChestTemplate.builder(5)

                .border(0, 0, 5, 9, outerPanes)
                .set(1, 2, lotterypokemon)
                .set(1, 4, claimBase)
                .set(1, 6, time)
                .set(1, 7, innerPanes)
                .set(3, 2, requirementSize)
                .set(3,3, requirementNature)
                .set(3, 4, requirementGender)
                .set(3, 5, requirementAbility)
                .set(3, 6, requirementStat)
                .set(1, 1, innerPanes)
                .set(1, 3 ,innerPanes)
                .set(1, 5, innerPanes)
                .set(2, 1, innerPanes)
                .set(2, 2, innerPanes)
                .set(2, 3, innerPanes)
                .set(2, 4, innerPanes)
                .set(2, 5, innerPanes)
                .set(2, 6, innerPanes)
                .set(2, 7, innerPanes)
                .set(3, 1, innerPanes)
                .set(3, 7, innerPanes)
                .build();

        return GooeyPage.builder()
                .template(template)
                .title(TextUtils.regex(_lang.titleMenu))
                .build();
    }
}
