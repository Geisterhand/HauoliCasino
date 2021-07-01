package eu.mccluster.hauolicasino.commands;

import ca.landonjw.gooeylibs2.api.UIManager;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.menu.PokeLotteryInventory;
import eu.mccluster.hauolicasino.utils.TextUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;


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
        if(!HauoliCasino.getModule().modulePokeLottery) {
            player.sendMessage(TextUtils.toText("[&dHauoliCasino&r] &4This module is deactivated by the server"));
            return;
        }


        if(args.length == 1) {
            if(TextUtils.hasPermission(player, "hauolicasino.forceopen")) {
                String targetplayer = args[0];
                MinecraftServer playserver = FMLCommonHandler.instance().getMinecraftServerInstance();
                EntityPlayerMP target = playserver.getPlayerList().getPlayerByUsername(targetplayer);
                if (target != null) {
                    UIManager.openUIForcefully(target, PokeLotteryInventory.createPokeLottery(target));
                } else {
                    player.sendMessage(TextUtils.toText("[&dHauoliCasino&r] &4Player not found."));
                }

            } else {
                player.sendMessage(TextUtils.toText("[&dHauoliCasino&r] &4You don't have permission to open the menu for other people."));
            }
        } else {
            UIManager.openUIForcefully((EntityPlayerMP) sender, PokeLotteryInventory.createPokeLottery(player));
        }

    }
}
