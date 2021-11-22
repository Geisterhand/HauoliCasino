package eu.mccluster.hauolicasino.config.pokelottery;

import eu.mccluster.hauolicasino.objects.LotteryObject;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class Data {

    @Comment("Don't touch any of this!!!")
    public List<String> playerList = new ArrayList<>();

    public List<LotteryObject> lotteryData = new ArrayList<>();

}
