package eu.mccluster.hauolilottery.config;
import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;

public class LootTableData extends Config {

    @Order(1)
    public String loot = "pixelmon:poke_ball";

    @Order(2)
    public int lootAmount = 1;

    @Order(3)
    public int lootRarity = 5;

    @Override
    public File getFile() {
        return null;
    }
}
