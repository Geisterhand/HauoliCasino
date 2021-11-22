package eu.mccluster.hauolicasino.config.pokelottery;


import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;


@ConfigSerializable
public class PokeLotteryConfig{


    @Comment("Toggles if the Lottery is able to choose legendary Pokemon as Lottery-Pokemon")
    public boolean legendaryLottery = true;

    @Comment("Cooldown for the next lottery. Add a m, h or d to the number to change the cooldown to Minutes, Hours or Days")
    public String cooldown = "24h";

    public BroadcastSettings bcSettings = new BroadcastSettings();

    public InventoryItems inventorySettings = new InventoryItems();

}
