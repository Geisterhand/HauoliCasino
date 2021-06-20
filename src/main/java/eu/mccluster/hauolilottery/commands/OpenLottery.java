package eu.mccluster.hauolilottery.commands;

import ca.landonjw.gooeylibs2.api.UIManager;
import eu.mccluster.hauolilottery.menu.LotteryInventory;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;


public class OpenLottery extends CommandBase {
    @Override
    public String getName() {
        return "haoulilottery";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/haoulilottery";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(4, "hauolilottery.openlottery");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        EntityPlayerMP player = (EntityPlayerMP) sender;
        UIManager.openUIForcefully((EntityPlayerMP) sender, LotteryInventory.createPage(player));
    }
}
