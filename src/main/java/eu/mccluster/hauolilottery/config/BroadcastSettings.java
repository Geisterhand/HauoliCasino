package eu.mccluster.hauolilottery.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;

public class BroadcastSettings extends Config {

    @Order(1)
    public boolean broadcastLottery = false;

    @Order(2)
    public String lotteryMessage = "[§dHauoliLottery§r] §6§lA new Lottery has started!";

    @Order(3)
    public boolean broadcastReminder = false;

    @Order(4)
    @Comment("Interval in minutes between the broadcasts. Ignore this if the broadcastReminder setting is false.")
    public int reminderInterval = 1;

    @Order(5)
    @Comment("Reminder Broadcast message. Ignore this if the broadcastReminder setting is false. Placeholders: %lotterypokemon%, %cooldownlottery%")
    public String reminderMessage = "[§dHauoliLottery§r] §6§lCurrent Lottery Pokemon: %lotterypokemon%. You have %cooldownlottery% left to show the pokemon!";

    @Override
    public File getFile() {
        return null;
    }
}
