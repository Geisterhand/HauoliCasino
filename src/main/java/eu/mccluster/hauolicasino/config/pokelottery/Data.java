package eu.mccluster.hauolicasino.config.pokelottery;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;
import eu.mccluster.hauolicasino.objects.LotteryObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Data extends Config {

    @Skip
    File _data;

    public Data(File file) { _data = file; }

    @Order(1)
    @Comment("Don't touch any of this!!!")
    public List<String> playerList = new ArrayList<>();

    @Order(2)
    public List<LotteryObject> lotteryData = new ArrayList<>();

    @Override
    public File getFile() {
        return _data;
    }
}
