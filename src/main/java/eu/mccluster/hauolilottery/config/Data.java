package eu.mccluster.hauolilottery.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;
import eu.mccluster.hauolilottery.objects.LotteryObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Data extends Config {

    @Skip
    File _playerData;

    public Data(File file) { _playerData = file; }

    @Order(1)
    public List<String> playerList = new ArrayList<>();

    @Order(2)
    public List<LotteryObject> lotteryData = new ArrayList<>();

    @Override
    public File getFile() {
        return _playerData;
    }
}
