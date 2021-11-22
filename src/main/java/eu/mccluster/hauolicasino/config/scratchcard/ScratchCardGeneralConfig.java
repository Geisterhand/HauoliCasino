package eu.mccluster.hauolicasino.config.scratchcard;


import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

@ConfigSerializable
public class ScratchCardGeneralConfig {


    @Comment("Chance that a button of the scratch card is reward")
    public int chance = 10;

    @Comment("Maximum amount of rewards per scratchcard")
    public int maxRewards = 2;

    @Comment("Cost for a single Scratchcard")
    public int cost = 100;

    public String blankItem = "minecraft:dirt";

    public String concealedItem = "minecraft:paper";

    public String gameItem = "minecraft:nether_star";

    public String unavailableItem = "minecraft:barrier";

    public int paneColor = 11;

}

