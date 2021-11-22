package eu.mccluster.hauolicasino.utils;

import eu.mccluster.hauolicasino.ConfigManagement;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryConfig;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryLangConfig;
import net.minecraft.entity.player.EntityPlayerMP;

import java.io.File;
import java.nio.file.Paths;
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
        text = text.replaceAll("%lotterypokemon%", GenLotteryPokemon.genPokemonName(HauoliCasino._currentLottery.get(0).getIndexNumber()));
        return TextUtils.regex(text);
    }

    public static String currentIdentifiers(String text) {
        text = text.replaceAll("%growth%", HauoliCasino._currentLottery.get(0).getGrowth().toString());
        text = text.replaceAll("%nature%", HauoliCasino._currentLottery.get(0).getNature().toString());
        text = text.replaceAll("%gender%", HauoliCasino._currentLottery.get(0).getGender().toString());
        text = text.replaceAll("%ability%", HauoliCasino._currentLottery.get(0).getAbility().get().getName());
        text = text.replaceAll("%stat%", HauoliCasino._currentLottery.get(0).getStat().toString() + " " + HauoliCasino._currentLottery.get(0).getStatHeight());
        return text;
    }

    public static List<String> currentGrowth(List<String> stringList) {
        List<String> returnList = new ArrayList<>();
        for(String s : stringList) {
            s = s.replaceAll("%growth%", HauoliCasino._currentLottery.get(0).getGrowth().toString());
            returnList.add(s);
        }
        return returnList;
    }

    public static List<String> currentNature(List<String> stringList) {
        List<String> returnList = new ArrayList<>();
        for(String s : stringList) {
            s = s.replaceAll("%nature%", HauoliCasino._currentLottery.get(0).getNature().toString());
            returnList.add(s);
        }
        return returnList;
    }

    public static List<String> currentGender(List<String> stringList) {
        List<String> returnList = new ArrayList<>();
        for(String s : stringList) {
            s = s.replaceAll("%gender%", HauoliCasino._currentLottery.get(0).getGender().toString());
            returnList.add(s);
        }
        return returnList;
    }

    public static List<String> currentAbility(List<String> stringList) {
        List<String> returnList = new ArrayList<>();
        for(String s : stringList) {
            s = s.replaceAll("%ability%", HauoliCasino._currentLottery.get(0).getAbility().get().getName());
            returnList.add(s);
        }
        return returnList;
    }

    public static List<String> currentStat(List<String> stringList) {
        List<String> returnList = new ArrayList<>();
        for(String s : stringList) {
            s = s.replaceAll("%stat%", HauoliCasino._currentLottery.get(0).getStat().toString() + " " + HauoliCasino._currentLottery.get(0).getStatHeight());
            returnList.add(s);
        }
        return returnList;
    }


    public static String remainingTime(String text) {

        Date time = new Date();
        String cooldown;
        String hour;
        String minute;

        PokeLotteryLangConfig lang = ConfigManagement.getInstance().loadConfig(PokeLotteryLangConfig.class, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "Lang.yml"));
        PokeLotteryConfig config = ConfigManagement.getInstance().loadConfig(PokeLotteryConfig.class, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "PokeLottery.yml"));

        long lastRotation = HauoliCasino._currentLottery.get(0).getDate().getTime();
        long remainingTime = time.getTime() - lastRotation;
        long configCooldown = TimeUtils.parseCooldown(config.cooldown);
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime);
        long toHours = (configCooldown / 60L) - 1L - TimeUnit.MILLISECONDS.toHours(remainingTime);

        if(toHours > 0L) {
            String cooldownHour = Long.toString(toHours);
            String cooldownMinutes = Long.toString(60L - (toMinutes - (TimeUnit.MILLISECONDS.toHours(remainingTime) * 60L)));

            if(toHours > 1L) {
                hour = lang.pluralHour;
            } else {
                hour = lang.singularHour;
            }

            if(toMinutes < 58L && toMinutes != 0L) {
                minute = lang.pluralMinute;
            } else if(toMinutes == 0) {
                toHours = toHours + 1L;
                cooldownHour = Long.toString(toHours);
                cooldown = cooldownHour + " " + hour;
                text = text.replaceAll("%cooldownlottery%", cooldown);
                return TextUtils.regex(text);
            } else {
                minute = lang.singularMinute;
            }
            cooldown = cooldownHour + " " + hour + " " + lang.participleAnd + " " + cooldownMinutes + " " + minute;
            text = text.replaceAll("%cooldownlottery%", cooldown);
            return TextUtils.regex(text);
        }

        if(toMinutes >= 1L && toMinutes < configCooldown) {
            minute = lang.pluralMinute;
        } else {
            minute = lang.singularMinute;
        }
        cooldown = Long.toString(configCooldown - toMinutes);
        text = text.replaceAll("%cooldownlottery%", cooldown + " " + minute);
        return TextUtils.regex(text);
    }

}
