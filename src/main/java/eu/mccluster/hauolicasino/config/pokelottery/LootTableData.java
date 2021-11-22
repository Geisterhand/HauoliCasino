package eu.mccluster.hauolicasino.config.pokelottery;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.io.File;

@ConfigSerializable
public class LootTableData{

    public String loot = "pixelmon:poke_ball";

    public int lootAmount = 1;

    public int lootRarity = 5;

}
