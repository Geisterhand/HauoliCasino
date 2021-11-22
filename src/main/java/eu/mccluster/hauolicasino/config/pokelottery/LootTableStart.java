package eu.mccluster.hauolicasino.config.pokelottery;


import net.minecraftforge.common.config.Config;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.io.File;

@ConfigSerializable
public class LootTableStart{



    @Comment("Toggles if the player receives extra loot if he fulfill all 5 identifiers")
    public boolean toggleExtraLoot = false;

    @Config.Comment("Extra loot the player gets out of the Loottable below. Ignore this if extraLoot is on false")
    public int extraLoot = 3;

    public LootTableConfig loottable = new LootTableConfig();

}
