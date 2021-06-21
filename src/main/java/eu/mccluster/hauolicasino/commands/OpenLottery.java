package eu.mccluster.hauolicasino.commands;

import ca.landonjw.gooeylibs2.api.UIManager;
import eu.mccluster.hauolicasino.menu.PokeLotteryInventory;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;


public class OpenLottery extends CommandBase {
    @Override
    public String getName() {
        return "pokelottery";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/pokelottery";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(4, "hauolicasino.openlottery");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        EntityPlayerMP player = (EntityPlayerMP) sender;
        UIManager.openUIForcefully((EntityPlayerMP) sender, PokeLotteryInventory.createPage(player));
    }
}
