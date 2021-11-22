package eu.mccluster.hauolicasino.config.pokelottery;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class PokeLotteryLangConfig {


    public String glassPanes = "§6§lHauiliCasino";

    public String requirmentTrue = "§2You fulfill this requirement!";

    public String requirementFalse = "§4You dont have the required identifier!";

    @Comment("Placerholder: %growth%")
    public List<String> reqGrowth = new ArrayList<>();

    @Comment("Placerholder: %nature%")
    public List<String> reqNature = new ArrayList<>();

    @Comment("Placerholder: %gender%")
    public List<String> reqGender = new ArrayList<>();

    @Comment("Placerholder: %ability%")
    public List<String> reqAbility = new ArrayList<>();

    @Comment("Placerholder: %stat%")
    public List<String> reqStat = new ArrayList<>();

    @Comment("With %loot% you return the amount of loot you get from the Loottable")
    public List<String> canClaim = new ArrayList<>();

    public List<String> alreadyClaimed = new ArrayList<>();

    public List<String> notOriginalTrainer = new ArrayList<>();

    public List<String> noLotteryPokemon = new ArrayList<>();

    public List<String> noIdentifiers = new ArrayList<>();

    public String titleGrowth = "§6§l§nGrowth";

    public String titleNature = "§6§l§nNature";

    public String titleGender = "§6§l§nGender";

    public String titleAbility = "§6§l§nAbility";

    public String titleIV = "§6§l§nIV-Stat";

    public String titleClaim = "§6§l§nClaim Lottery";

    public String titleTime = "§6§l§nLottery Time";

    public String titleMenu = "§c§lHaouliCasino";

    public String colorPokemon = "§6§l§n";

    public String singularMinute = "minute";

    public String pluralMinute = "minutes";

    public String singularHour = "hour";

    public String pluralHour = "hours";

    public String participleAnd = "and";

    @Comment("With %cooldownlottery% you return the remaining time of the Lottery.")
    public String remainingTime = "§2§lThe next lottery starts in %cooldownlottery%";

    public PokeLotteryLangConfig() {
        reqGrowth.add("§6Required Growth: %growth%");
        reqNature.add("§6Required Nature: %nature%");
        reqGender.add("§6Required Gender: %gender%");
        reqAbility.add("§6Required Ability: %ability%");
        reqStat.add("§6Required IV-Stat: %stat%");
        canClaim.add("§6You'll receive %loot% out of 5 rewards");
        canClaim.add("§4§cYou cant claim the rewards again for the remaining duration");
        canClaim.add("§4§cof the lottery, if you take them now!");
        alreadyClaimed.add("§4You already claimed the lottery!");
        notOriginalTrainer.add("§4You're not the original Trainer of the pokemon!");
        noLotteryPokemon.add("§4You don't have the lottery pokemon in your team!");
        noIdentifiers.add("§4You have none of the required identifiers!");

    }

}
