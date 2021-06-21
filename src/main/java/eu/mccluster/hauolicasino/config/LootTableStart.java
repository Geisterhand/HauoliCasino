package eu.mccluster.hauolicasino.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class LootTableStart extends Config {

    @Skip
    File _lootFile;

    public LootTableStart(File file) { _lootFile = file; }

    @Order(1)
    @Comment("Toggles if the player receives extra loot if he fulfill all 5 identifiers")
    public boolean toggleExtraLoot = false;

    @Order(2)
    @Comment("Extra loot the player gets out of the Loottable below. Ignore this if extraLoot is on false")
    public int extraLoot = 3;


    @Order(1)
    public LootTableConfig loottable = new LootTableConfig();

    @Override
    public File getFile() {
        return _lootFile;
    }
}
