package eu.mccluster.hauolilottery.menu;

import ca.landonjw.gooeylibs2.api.button.Button;
import ca.landonjw.gooeylibs2.api.button.GooeyButton;
import ca.landonjw.gooeylibs2.api.page.GooeyPage;
import ca.landonjw.gooeylibs2.api.page.Page;
import ca.landonjw.gooeylibs2.api.template.Template;
import ca.landonjw.gooeylibs2.api.template.types.ChestTemplate;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.config.PixelmonBlocks;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.config.PixelmonItemsBadges;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import eu.mccluster.hauolilottery.HauoliLottery;
import eu.mccluster.hauolilottery.config.LangConfig;
import eu.mccluster.hauolilottery.config.Data;
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
        LotteryObject _object = HauoliLottery._currentLottery.get(0);

        short pokemonIndexNumber = _object.getIndexNumber();
        Pokemon pokemon = GenLotteryPokemon.genPokemon(pokemonIndexNumber);
        EnumGrowth size = _object.getGrowth();
        EnumNature nature = _object.getNature();
        Gender gender = _object.getGender();
        AbilityBase ability = _object.getAbility().get();
        StatsType stats = _object.getStat();

        AtomicBoolean claimable = new AtomicBoolean(false);

        ItemStack claimItem;
        ItemStack sizeItem;
        ItemStack natureItem;
        ItemStack genderItem;
        ItemStack abilityItem;
        ItemStack statItem;

        List<String> loreClaim = new ArrayList<>();
        List<String> loreSize = new ArrayList<>();
        List<String> loreNature = new ArrayList<>();
        List<String> loreGender = new ArrayList<>();
        List<String> loreAbility = new ArrayList<>();
        List<String> loreStat = new ArrayList<>();
        List<String> loreTime = new ArrayList<>();

        loreTime.add(Placeholder.remainingTime(_lang.remainingTime));

        List<Boolean> checks = LotteryUtils.checkPokemon(playerMP, pokemon, size, nature, gender, ability, stats);

        int equals = LotteryUtils.equalSize(checks);

        if (_data.playerList.contains(playerMP.getUniqueID().toString()) || !LotteryUtils.hasLotteryPokemon(playerMP, pokemon)) {
            claimItem = new ItemStack(Blocks.BARRIER);
            claimable.set(false);
            loreClaim.add(_lang.cantClaim);
        } else {
            claimItem = new ItemStack(PixelmonBlocks.assemblyUnit);
            claimable.set(true);
            loreClaim.add(Placeholder.lootAmount(_lang.canClaim, equals));
        }

        if(!checks.get(0)) {
            sizeItem = new ItemStack(Blocks.BARRIER);
            loreSize.add(_lang.requirementFalse);
            loreSize.add(_lang.reqGrowth + " " + _data.lotteryData.get(0).getGrowth().name());
        } else {
            sizeItem = new ItemStack(PixelmonItemsBadges.goldKnowledgeSymbol);
            loreSize.add(_lang.requirmentTrue);
            loreSize.add(_lang.reqGrowth + " " + _data.lotteryData.get(0).getGrowth().name());
        }

        if(!checks.get(1)) {
            natureItem = new ItemStack(Blocks.BARRIER);
            loreNature.add(_lang.requirementFalse);
            loreNature.add(_lang.reqNature + " " + _data.lotteryData.get(0).getNature().name());
        } else {
            natureItem = new ItemStack(PixelmonItemsBadges.goldKnowledgeSymbol);
            loreNature.add(_lang.requirmentTrue);
            loreNature.add(_lang.reqNature + " " + _data.lotteryData.get(0).getNature().name());
        }

        if(!checks.get(2)) {
            genderItem = new ItemStack(Blocks.BARRIER);
            loreGender.add(_lang.requirementFalse);
            loreGender.add(_lang.reqGender + " " + _data.lotteryData.get(0).getGender().name());
        } else {
            genderItem = new ItemStack(PixelmonItemsBadges.goldKnowledgeSymbol);
            loreGender.add(_lang.requirmentTrue);
            loreGender.add(_lang.reqGender + " " + _data.lotteryData.get(0).getGender().name());
        }

        if(!checks.get(3)) {
                abilityItem = new ItemStack(Blocks.BARRIER);
                loreAbility.add(_lang.requirementFalse);
                loreAbility.add(_lang.reqAbility + " " + ability.getName());
            } else {
                abilityItem = new ItemStack(PixelmonItemsBadges.goldKnowledgeSymbol);
                loreAbility.add(_lang.requirmentTrue);
                loreAbility.add(_lang.reqAbility + " " + ability.getName());
            }

        if(!checks.get(4)) {
            statItem = new ItemStack(Blocks.BARRIER);
            loreStat.add(_lang.requirementFalse);
            loreStat.add(_lang.reqStat + " " + _data.lotteryData.get(0).getStat().name() + " " + _data.lotteryData.get(0).getStatHeight());
        } else {
            statItem = new ItemStack(PixelmonItemsBadges.goldKnowledgeSymbol);
            loreSize.add(_lang.requirmentTrue);
            loreStat.add(_lang.reqStat + " " + _data.lotteryData.get(0).getStat().name() + " " + _data.lotteryData.get(0).getStatHeight());
        }

        Button pane = GooeyButton.builder()
                .display(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0))
                .title(TextUtils.regex(_lang.glassPanes))
                .build();

        Button lotterypokemon = GooeyButton.builder()
                .display(GenLotteryPokemon.genPokemonSprite(pokemonIndexNumber))
                .title(TextUtils.regex(_lang.colorPokemon + GenLotteryPokemon.genPokemonName(pokemonIndexNumber)))
                .build();

        Button claimBase = GooeyButton.builder()
                .display(claimItem)
                .title(TextUtils.regex(_lang.titleClaim))
                .lore(loreClaim)
                .onClick((buttonAction -> {
                    if(claimable.get()) {
                        EntityPlayerMP actionPlayer = buttonAction.getPlayer();
                        LootUtils.genLoot(actionPlayer, equals);
                        for(int i = 0; i < HauoliLottery.getPlayerLoot().get(actionPlayer.getUniqueID()).size(); i++) {
                            actionPlayer.inventory.addItemStackToInventory(HauoliLottery.getPlayerLoot().get(playerMP.getUniqueID()).get(i));
                        }
                        HauoliLottery.getData().playerList.add(playerMP.getUniqueID().toString());
                        HauoliLottery.getData().save();
                        claimable.set(false);
                    }
                }))
                .build();

        Button time = GooeyButton.builder()
                .display(new ItemStack(PixelmonItems.hourglassGold))
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

                .border(0, 0, 5, 9, pane)
                .set(1, 2, lotterypokemon)
                .set(1, 4, claimBase)
                .set(1, 6, time)
                .set(3, 2, requirementSize)
                .set(3,3, requirementNature)
                .set(3, 4, requirementGender)
                .set(3, 5, requirementAbility)
                .set(3, 6, requirementStat)
                .build();

        return GooeyPage.builder()
                .template(template)
                .title(TextUtils.regex(_lang.titleMenu))
                .build();
    }
}
