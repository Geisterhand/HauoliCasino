package eu.mccluster.hauolilottery.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class LangConfig extends Config {

    @Skip
    File _langFile;

    public LangConfig(File file) { _langFile = file; }

    @Order(1)
    public String glassPanes = "§6§lHauiliLottery";

    @Order(2)
    public String requirmentTrue = "§2You fulfill this requirement!";

    @Order(3)
    public String requirementFalse = "§4You dont have the required identifier!";

    @Order(4)
    public String reqGrowth = "§6Required Growth:";

    @Order(5)
    public String reqNature = "§6Required Nature:";

    @Order(6)
    public String reqGender = "§6Required Gender:";

    @Order(7)
    public String reqAbility = "§6Required Ability:";

    @Order(8)
    public String reqStat = "§6Required IV-Stat:";

    @Order(9)
    @Comment("With %loot% you return the amount of loot you get from the Loottable")
    public String canClaim = "§6You'll receive %loot% out of 5 rewards";

    @Order(10)
    public String cantClaim = "§4You dont have the requirements to claim the rewards!";

    @Order(11)
    public String titleGrowth = "§6§l§nGrowth";

    @Order(12)
    public String titleNature = "§6§l§nNature";

    @Order(13)
    public String titleGender = "§6§l§nGender";

    @Order(14)
    public String titleAbility = "§6§l§nAbility";

    @Order(15)
    public String titleIV = "§6§l§nIV-Stat";

    @Order(16)
    public String titleClaim = "§6§l§nClaim Lottery";

    @Order(17)
    public String titleTime = "§6§l§nLottery Time";

    @Order(18)
    public String titleMenu = "§c§lHaouliLottery";

    @Order(19)
    public String colorPokemon = "§6§l§n";

    @Order(19)
    public String singularMinute = "minute";

    @Order(20)
    public String pluralMinute = "minutes";

    @Order(21)
    public String singularHour = "hour";

    @Order(22)
    public String pluralHour = "hours";

    @Order(23)
    @Comment("With %cooldownlottery% you return the remaining time of the Lottery.")
    public String remainingTime = "§2§lThe next lottery starts in %cooldownlottery%";

    @Override
    public File getFile() {
        return _langFile;
    }
}
