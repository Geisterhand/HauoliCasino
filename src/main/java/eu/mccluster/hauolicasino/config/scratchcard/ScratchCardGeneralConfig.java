package eu.mccluster.hauolicasino.config.scratchcard;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class ScratchCardGeneralConfig  extends Config {

    @Skip
    File _configFile;

    public ScratchCardGeneralConfig(File file) { _configFile = file; }

    @Order(1)
    @Comment("Chance that a button of the scratch card is reward")
    public int chance = 10;

    @Order(2)
    @Comment("Maximum amount of rewards per scratchcard")
    public int maxRewards = 2;

    @Order(3)
    @Comment("Cost for a single Scratchcard")
    public int cost = 100;

    @Order(4)
    public String blankItem = "minecraft:dirt";

    @Order(5)
    public String concealedItem = "minecraft:paper";

    @Order(6)
    public String gameItem = "minecraft:nether_star";

    @Order(7)
    public String unavailableItem = "minecraft:barrier";

    @Order(8)
    public int paneColor = 11;

    @Override
    public File getFile() {
        return _configFile;
    }
}

