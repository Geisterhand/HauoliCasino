package eu.mccluster.hauolicasino.config.pokelottery;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PokeLotteryLangConfig extends Config {

    @Skip
    File _langFile;

    @Order(1)
    public String glassPanes = "§6§lHauiliCasino";

    @Order(2)
    public String requirmentTrue = "§2You fulfill this requirement!";

    @Order(3)
    public String requirementFalse = "§4You dont have the required identifier!";

    @Order(4)
    @Comment("Placerholder: %growth%")
    public List<String> reqGrowth = new ArrayList<>();

    @Order(5)
    @Comment("Placerholder: %nature%")
    public List<String> reqNature = new ArrayList<>();

    @Order(6)
    @Comment("Placerholder: %gender%")
    public List<String> reqGender = new ArrayList<>();

    @Order(7)
    @Comment("Placerholder: %ability%")
    public List<String> reqAbility = new ArrayList<>();

    @Order(8)
    @Comment("Placerholder: %stat%")
    public List<String> reqStat = new ArrayList<>();

    @Order(9)
    @Comment("With %loot% you return the amount of loot you get from the Loottable")
    public List<String> canClaim = new ArrayList<>();

    @Order(10)
    public List<String> alreadyClaimed = new ArrayList<>();

    @Order(11)
    public List<String> notOriginalTrainer = new ArrayList<>();

    @Order(12)
    public List<String> noLotteryPokemon = new ArrayList<>();

    @Order(13)
    public List<String> noIdentifiers = new ArrayList<>();

    @Order(13)
    public String titleGrowth = "§6§l§nGrowth";

    @Order(14)
    public String titleNature = "§6§l§nNature";

    @Order(15)
    public String titleGender = "§6§l§nGender";

    @Order(16)
    public String titleAbility = "§6§l§nAbility";

    @Order(17)
    public String titleIV = "§6§l§nIV-Stat";

    @Order(18)
    public String titleClaim = "§6§l§nClaim Lottery";

    @Order(19)
    public String titleTime = "§6§l§nLottery Time";

    @Order(20)
    public String titleMenu = "§c§lHaouliCasino";

    @Order(21)
    public String colorPokemon = "§6§l§n";

    @Order(22)
    public String singularMinute = "minute";

    @Order(23)
    public String pluralMinute = "minutes";

    @Order(24)
    public String singularHour = "hour";

    @Order(25)
    public String pluralHour = "hours";

    @Order(26)
    public String participleAnd = "and";

    @Order(27)
    @Comment("With %cooldownlottery% you return the remaining time of the Lottery.")
    public String remainingTime = "§2§lThe next lottery starts in %cooldownlottery%";

    public PokeLotteryLangConfig(File file) {
        _langFile = file;
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


    @Override
    public File getFile() {
        return _langFile;
    }
}
