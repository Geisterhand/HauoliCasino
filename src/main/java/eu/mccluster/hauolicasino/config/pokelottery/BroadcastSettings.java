package eu.mccluster.hauolicasino.config.pokelottery;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.io.File;

@ConfigSerializable
public class BroadcastSettings{

    @Comment("Toggles if a new PokeLottery will be broadcasted")
    public boolean broadcastLottery = false;

    public String lotteryMessage = "[§dHauoliCasino§r] §6§lA new Lottery has started!";

    @Comment("Toggles if player should be remindered of the current PokeLottery")
    public boolean broadcastReminder = false;

    @Comment("Interval in minutes between the broadcasts. Ignore this if the broadcastReminder setting is false.")
    public int reminderInterval = 1;

    @Comment("Reminder Broadcast message. Ignore this if the broadcastReminder setting is false. Placeholders: %lotterypokemon%, %cooldownlottery%")
    public String reminderMessage = "[§dHauoliCasino§r] §6§lCurrent Lottery Pokemon: %lotterypokemon%. You have %cooldownlottery% left to show the pokemon!";


}
