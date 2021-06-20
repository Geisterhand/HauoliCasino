package eu.mccluster.hauolilottery.commands;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegistry {

    public static void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new OpenLottery());
        event.registerServerCommand(new ReloadLottery());

    }
}
