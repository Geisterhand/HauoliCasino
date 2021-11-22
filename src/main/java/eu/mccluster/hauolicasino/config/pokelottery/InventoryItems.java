package eu.mccluster.hauolicasino.config.pokelottery;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;


@ConfigSerializable
public class InventoryItems{

    @Comment("Color of the inner glass panes")
    public int innerGlassPaneColor = 0;

    @Comment("Color of the outside glass panes")
    public int outsideGlassPaneColor = 1;

    @Comment("Item when a requirement is not fulfilled")
    public String growthNotFulfilled = "pixelmon:silver_knowledge_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String natureNotFulfilled = "pixelmon:silver_guts_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String genderNotFulfilled = "pixelmon:silver_tactics_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String abilityNotFulfilled = "pixelmon:silver_luck_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String ivNotFulfilled = "pixelmon:silver_spirits_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String growthFulfilled = "pixelmon:gold_knowledge_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String natureFulfilled = "pixelmon:gold_guts_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String genderFulfilled = "pixelmon:gold_tactics_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String abilityFulfilled = "pixelmon:gold_luck_symbol";

    @Comment("Item when a requirement is not fulfilled")
    public String ivFulfilled = "pixelmon:gold_spirits_symbol";

    @Comment("Item for the Identifiers (Growth, nature etc.")
    public String notClaimable = "minecraft:barrier";

    @Comment("Item for the claim button")
    public String claimItem = "pixelmon:reassembly_unit";

    @Comment("Item for the time button")
    public String timeItem = "pixelmon:hourglass_gold";

}
