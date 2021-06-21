package eu.mccluster.hauolicasino.config;

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
    public String growthNotFulfilled = "pixelmon:silver_knowledge_symbol";

    @Order(4)
    @Comment("Item when a requirement is not fulfilled")
    public String natureNotFulfilled = "pixelmon:silver_guts_symbol";

    @Order(5)
    @Comment("Item when a requirement is not fulfilled")
    public String genderNotFulfilled = "pixelmon:silver_tactics_symbol";

    @Order(6)
    @Comment("Item when a requirement is not fulfilled")
    public String abilityNotFulfilled = "pixelmon:silver_luck_symbol";

    @Order(7)
    @Comment("Item when a requirement is not fulfilled")
    public String ivNotFulfilled = "pixelmon:silver_spirits_symbol";

    @Order(8)
    @Comment("Item when a requirement is not fulfilled")
    public String growthFulfilled = "pixelmon:gold_knowledge_symbol";

    @Order(9)
    @Comment("Item when a requirement is not fulfilled")
    public String natureFulfilled = "pixelmon:gold_guts_symbol";

    @Order(10)
    @Comment("Item when a requirement is not fulfilled")
    public String genderFulfilled = "pixelmon:gold_tactics_symbol";

    @Order(11)
    @Comment("Item when a requirement is not fulfilled")
    public String abilityFulfilled = "pixelmon:gold_luck_symbol";

    @Order(12)
    @Comment("Item when a requirement is not fulfilled")
    public String ivFulfilled = "pixelmon:gold_spirits_symbol";

    @Order(13)
    @Comment("Item for the Identifiers (Growth, nature etc.")
    public String notClaimable = "minecraft:barrier";

    @Order(14)
    @Comment("Item for the claim button")
    public String claimItem = "pixelmon:reassembly_unit";

    @Order(15)
    @Comment("Item for the time button")
    public String timeItem = "pixelmon:hourglass_gold";


    @Override
    public File getFile() {
        return null;
    }
}
