package eu.mccluster.hauolilottery.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;

public class BroadcastSettings extends Config {

    @Order(1)
    public boolean broadcastLottery = false;

    @Order(2)
    public String lotteryMessage = "§6§lA new Lottery has started!";

    @Override
    public File getFile() {
        return null;
    }
}
