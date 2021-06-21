package eu.mccluster.hauolilottery.utils;

import eu.mccluster.hauolilottery.HauoliLottery;
import net.minecraft.entity.player.EntityPlayerMP;
import org.lwjgl.Sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Placeholder {

    public static String lootAmount(String text, int equals) {
        text = text.replaceAll("%loot%", Integer.toString(equals));
        return TextUtils.regex(text);
    }

    public static String parsePlayer(String text, EntityPlayerMP player) {
        text = text.replaceAll("%player%", player.getName());
        return text;
    }

    public static List<String> lootAmountList(List<String> stringList, int equals) {
        List<String> returnList = new ArrayList<>();
        for(String s : stringList) {
            if(s.contains("%loot%")) {
                s = s.replaceAll("%loot%", Integer.toString(equals));
            }
            returnList.add(s);
        }
        return returnList;
    }

    public static String currentPokemon(String text) {
        text = text.replaceAll("%lotterypokemon%", GenLotteryPokemon.genPokemonName(HauoliLottery._currentLottery.get(0).getIndexNumber()));
        return TextUtils.regex(text);
    }

    public static String remainingTime(String text) {

        Date time = new Date();
        String cooldown;
        String hour;
        String minute;

        long lastRotation = HauoliLottery._currentLottery.get(0).getDate().getTime();
        long remainingTime = time.getTime() - lastRotation;
        long configCooldown = TimeUtils.parseCooldown(HauoliLottery.getConfig().cooldown);
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime);
        long toHours = (configCooldown / 60L) - 1L - TimeUnit.MILLISECONDS.toHours(remainingTime);

        if(toHours > 0L) {
            String cooldownHour = Long.toString(toHours);
            String cooldownMinutes = Long.toString(60L - (toMinutes - (TimeUnit.MILLISECONDS.toHours(remainingTime) * 60L)));

            if(toHours > 1L) {
                hour = HauoliLottery.getLang().pluralHour;
            } else {
                hour = HauoliLottery.getLang().singularHour;
            }

            if(toMinutes < 58L && toMinutes != 0L) {
                minute = HauoliLottery.getLang().pluralMinute;
            } else if(toMinutes == 0) {
                toHours = toHours + 1L;
                cooldownHour = Long.toString(toHours);
                cooldown = cooldownHour + " " + hour;
                text = text.replaceAll("%cooldownlottery%", cooldown);
                return TextUtils.regex(text);
            } else {
                minute = HauoliLottery.getLang().singularMinute;
            }
            cooldown = cooldownHour + " " + hour + " " + HauoliLottery.getLang().participleAnd + " " + cooldownMinutes + " " + minute;
            text = text.replaceAll("%cooldownlottery%", cooldown);
            return TextUtils.regex(text);
        }

        if(toMinutes >= 1L && toMinutes < configCooldown) {
            minute = HauoliLottery.getLang().pluralMinute;
        } else {
            minute = HauoliLottery.getLang().singularMinute;
        }
        cooldown = Long.toString(configCooldown - toMinutes);
        text = text.replaceAll("%cooldownlottery%", cooldown + " " + minute);
        return TextUtils.regex(text);
    }

}
