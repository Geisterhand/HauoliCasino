package eu.mccluster.hauolilottery.commands;

import eu.mccluster.hauolilottery.HauoliLottery;
import eu.mccluster.hauolilottery.utils.TextUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class ReloadLottery extends CommandBase {
    @Override
    public String getName() {
        return "hauolireload";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(4, "hauolilottery.reload");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/hauolireload";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        HauoliLottery.getInstance().onReload();
        sender.sendMessage(TextUtils.toText("[&dHauoliLottery&r] &4Successfully reloaded Mod."));
    }
}
