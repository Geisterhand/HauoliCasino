package eu.mccluster.hauolicasino.commands;

import eu.mccluster.hauolicasino.menu.FreeScratchCardInventory;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommandRegistry {

    public static void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new OpenLottery());
        event.registerServerCommand(new ReloadLottery());
        event.registerServerCommand(new ForcePokeLottery());
        event.registerServerCommand(new CustomLottery());
        event.registerServerCommand(new OpenScratchCard());
        event.registerServerCommand(new OpenFreeScratchCard());
    }
}
