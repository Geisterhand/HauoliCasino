package eu.mccluster.hauolilottery.menu;

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
import eu.mccluster.hauolilottery.HauoliLottery;
import eu.mccluster.hauolilottery.config.LangConfig;
import eu.mccluster.hauolilottery.config.Data;
import eu.mccluster.hauolilottery.config.MainConfig;
import eu.mccluster.hauolilottery.objects.LotteryObject;
import eu.mccluster.hauolilottery.utils.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LotteryInventory {

    public static Page createPage(EntityPlayerMP playerMP) {

        LangConfig _lang = HauoliLottery.getLang();
        Data _data = HauoliLottery.getData();
        MainConfig _config = HauoliLottery.getConfig();
        LotteryObject _object = HauoliLottery._currentLottery.get(0);

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
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
            claimable.set(false);
            loreClaim = _lang.alreadyClaimed;
        } else if(claimable.get() && !LotteryUtils.hasLotteryPokemon(playerMP, pokemon)) {
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
            claimable.set(false);
            loreClaim = _lang.noLotteryPokemon;
        }  else if(claimable.get() && equals == 0) {
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
            claimable.set(false);
            loreClaim = _lang.noIdentifiers;
        } else if(claimable.get() && _config.originalTrainer ) {
            claimable.set(LotteryUtils.isOriginalTrainer(playerMP, pokemon));
            if (claimable.get()) {
                claimItem = LootUtils.itemStackFromType(_config.inventorySettings.claimItem, 1);
                loreClaim = _lang.canClaim;
            } else {
                claimItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
                loreClaim = _lang.notOriginalTrainer;
            }
        } else {
            claimItem = LootUtils.itemStackFromType(_config.inventorySettings.claimItem, 1);
            claimable.set(true);
            loreClaim = _lang.canClaim;
        }

        if(!checks.get(0)) {
            sizeItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
            loreSize.add(_lang.requirementFalse);
            loreSize.add(_lang.reqGrowth + " " + _data.lotteryData.get(0).getGrowth().name());
        } else {
            sizeItem = LootUtils.itemStackFromType(_config.inventorySettings.identifierItem, 1);
            loreSize.add(_lang.requirmentTrue);
            loreSize.add(_lang.reqGrowth + " " + _data.lotteryData.get(0).getGrowth().name());
        }

        if(!checks.get(1)) {
            natureItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
            loreNature.add(_lang.requirementFalse);
            loreNature.add(_lang.reqNature + " " + _data.lotteryData.get(0).getNature().name());
        } else {
            natureItem = LootUtils.itemStackFromType(_config.inventorySettings.identifierItem, 1);
            loreNature.add(_lang.requirmentTrue);
            loreNature.add(_lang.reqNature + " " + _data.lotteryData.get(0).getNature().name());
        }

        if(!checks.get(2)) {
            genderItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
            loreGender.add(_lang.requirementFalse);
            loreGender.add(_lang.reqGender + " " + _data.lotteryData.get(0).getGender().name());
        } else {
            genderItem = LootUtils.itemStackFromType(_config.inventorySettings.identifierItem, 1);
            loreGender.add(_lang.requirmentTrue);
            loreGender.add(_lang.reqGender + " " + _data.lotteryData.get(0).getGender().name());
        }

        if(!checks.get(3)) {
                abilityItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
                loreAbility.add(_lang.requirementFalse);
                loreAbility.add(_lang.reqAbility + " " + ability.getName());
            } else {
                abilityItem = LootUtils.itemStackFromType(_config.inventorySettings.identifierItem, 1);
                loreAbility.add(_lang.requirmentTrue);
                loreAbility.add(_lang.reqAbility + " " + ability.getName());
            }

        if(!checks.get(4)) {
            statItem = LootUtils.itemStackFromType(_config.inventorySettings.notClaimableItem, 1);
            loreStat.add(_lang.requirementFalse);
            loreStat.add(_lang.reqStat + " " + _data.lotteryData.get(0).getStat().name() + " " + _data.lotteryData.get(0).getStatHeight());
        } else {
            statItem = LootUtils.itemStackFromType(_config.inventorySettings.identifierItem, 1);
            loreStat.add(_lang.requirmentTrue);
            loreStat.add(_lang.reqStat + " " + _data.lotteryData.get(0).getStat().name() + " " + _data.lotteryData.get(0).getStatHeight());
        }

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
                        HauoliLottery.getData().playerList.add(playerMP.getUniqueID().toString());
                        HauoliLottery.getData().save();
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
                .lore(loreSize)
                .build();

        Button requirementNature = GooeyButton.builder()
                .display(natureItem)
                .title(TextUtils.regex(_lang.titleNature))
                .lore(loreNature)
                .build();

        Button requirementGender = GooeyButton.builder()
                .display(genderItem)
                .title(TextUtils.regex(_lang.titleGender))
                .lore(loreGender)
                .build();

        Button requirementAbility = GooeyButton.builder()
                .display(abilityItem)
                .title(TextUtils.regex(_lang.titleAbility))
                .lore(loreAbility)
                .build();

        Button requirementStat = GooeyButton.builder()
                .display(statItem)
                .title(TextUtils.regex(_lang.titleIV))
                .lore(loreStat)
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
