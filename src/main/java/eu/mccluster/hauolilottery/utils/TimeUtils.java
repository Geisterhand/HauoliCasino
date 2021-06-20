package eu.mccluster.hauolilottery.utils;

public class TimeUtils {

    public static long parseCooldown(String cooldown) {
        String cooldownString = cooldown;
        long _cooldown;

        if(cooldownString == null) {
            cooldownString = "24h";
        }
        if(cooldownString.contains("m")) {
            _cooldown = Integer.parseInt(cooldownString.replace("m", ""));
            return _cooldown;
        } else if(cooldownString.contains("h")) {
            _cooldown = Integer.parseInt(cooldownString.replace("h", "")) * 60L;
            return _cooldown;
        } else if(cooldownString.contains("d")) {
            _cooldown = Integer.parseInt(cooldownString.replace("d", "")) * 1440L;
            return _cooldown;
        } else {
            _cooldown = Integer.parseInt(cooldown) * 60L;
        }
        return _cooldown;
    }
}
