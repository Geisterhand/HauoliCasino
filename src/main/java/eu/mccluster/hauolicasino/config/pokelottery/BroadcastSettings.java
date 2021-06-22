package eu.mccluster.hauolicasino.config.pokelottery;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;

public class BroadcastSettings extends Config {

    @Order(1)
    @Comment("Toggles if a new PokeLottery will be broadcasted")
    public boolean broadcastLottery = false;

    @Order(2)
    public String lotteryMessage = "[§dHauoliCasino§r] §6§lA new Lottery has started!";

    @Order(3)
    @Comment("Toggles if player should be remindered of the current PokeLottery")
    public boolean broadcastReminder = false;

    @Order(4)
    @Comment("Interval in minutes between the broadcasts. Ignore this if the broadcastReminder setting is false.")
    public int reminderInterval = 1;

    @Order(5)
    @Comment("Reminder Broadcast message. Ignore this if the broadcastReminder setting is false. Placeholders: %lotterypokemon%, %cooldownlottery%")
    public String reminderMessage = "[§dHauoliCasino§r] §6§lCurrent Lottery Pokemon: %lotterypokemon%. You have %cooldownlottery% left to show the pokemon!";

    @Override
    public File getFile() {
        return null;
    }
}
