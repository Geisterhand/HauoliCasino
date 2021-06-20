package eu.mccluster.hauolilottery.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class MainConfig extends Config {

    @Skip
    File _configFile;

    public MainConfig(File file) { _configFile = file; }

    @Order(1)
    @Comment("Cooldown to collect the Pokestop again. Add a m, h or d to the number to change the cooldown to Minutes, Hours or Days")
    public String cooldown = "24h";

    @Order(2)
    public BroadcastSettings bcSettings = new BroadcastSettings();

    @Override
    public File getFile() {
        return _configFile;
    }
}
