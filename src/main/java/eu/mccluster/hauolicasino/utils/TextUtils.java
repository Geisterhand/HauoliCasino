package eu.mccluster.hauolicasino.utils;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.server.permission.PermissionAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {

    private static final String regex = "&(?=[0-9a-ff-or])";
    private static final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

    public static ITextComponent toText(String text) {
        return TextSerializer.parse(text);
    }

    public static String regex(String text) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            text = text.replaceAll(regex, "§");
        }
        return text;
    }

    public static List<String> regexList(List<String> stringList) {
        List<String> returnList = new ArrayList<>();
        for (String s : stringList) {
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                s = s.replaceAll(regex, "§");
            }
            returnList.add(s);
        }
        return returnList;
    }

    public static void broadcast(String broadcast) {
        MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
        for(EntityPlayerMP p : server.getPlayerList().getPlayers()) {
            p.sendMessage(toText(regex(broadcast)));
        }
    }

    public static boolean hasPermission(EntityPlayerMP player, String permissionNode) {
        return (PermissionAPI.hasPermission(player, permissionNode) || player.canUseCommand(4, permissionNode));
    }
}
