package eu.mccluster.hauolicasino.config.pokelottery;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LootTableConfig extends Config {

    @Order(1)
    @Comment("Default Loottable for PokeLottery")
    public List<LootTableData> lootData = new ArrayList<>();

    @Order(2)
    @Comment("Loottable for extra loots")
    public List<LootTableData> extraLootData = new ArrayList<>();

    public LootTableConfig() {

        lootData.add(new LootTableData());
        extraLootData.add(new LootTableData());
    }


    @Override
    public File getFile() {
        return null;
    }
}
