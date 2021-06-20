package eu.mccluster.hauolilottery.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class LootTableStart extends Config {

    @Skip
    File _lootFile;

    public LootTableStart(File file) { _lootFile = file; }

    @Order(1)
    public LootTableConfig loottable = new LootTableConfig();

    @Override
    public File getFile() {
        return _lootFile;
    }
}
