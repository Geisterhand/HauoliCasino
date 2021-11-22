package eu.mccluster.hauolicasino.config.pokelottery;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class LootTableConfig{

    @Comment("Default Loottable for PokeLottery")
    public List<LootTableData> lootData = new ArrayList<>();

    @Comment("Loottable for extra loots")
    public List<LootTableData> extraLootData = new ArrayList<>();

    public LootTableConfig() {

        lootData.add(new LootTableData());
        extraLootData.add(new LootTableData());
    }


}
