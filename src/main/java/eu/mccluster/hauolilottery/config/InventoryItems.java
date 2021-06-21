package eu.mccluster.hauolilottery.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;

public class InventoryItems extends Config {

    @Order(1)
    @Comment("Color of the inner glass panes")
    public int innerGlassPaneColor = 0;

    @Order(2)
    @Comment("Color of the outside glass panes")
    public int outsideGlassPaneColor = 1;

    @Order(3)
    @Comment("Item when a requirement is not fulfilled")
    public String notClaimableItem = "minecraft:barrier";

    @Order(4)
    @Comment("Item for the Identifiers (Growth, nature etc.")
    public String identifierItem = "pixelmon:gold_knowledge_symbol";

    @Order(5)
    @Comment("Item for the claim button")
    public String claimItem = "pixelmon:reassembly_unit";

    @Order(6)
    @Comment("Item for the time button")
    public String timeItem = "pixelmon:hourglass_gold";


    @Override
    public File getFile() {
        return null;
    }
}
