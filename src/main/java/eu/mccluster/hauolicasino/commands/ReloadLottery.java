package eu.mccluster.hauolicasino.commands;

import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.utils.TextUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class ReloadLottery extends CommandBase {
    @Override
    public String getName() {
        return "lotteryreload";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(4, "hauolicasino.reload");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/lotteryreload";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        HauoliCasino.getInstance().onReload();
        sender.sendMessage(TextUtils.toText("[&dHauoliLottery&r] &4Successfully reloaded Mod."));
    }
}
