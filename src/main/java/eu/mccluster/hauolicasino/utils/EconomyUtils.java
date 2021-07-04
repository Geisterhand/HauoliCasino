package eu.mccluster.hauolicasino.utils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.economy.IPixelmonBankAccount;
import com.pixelmonmod.pixelmon.api.economy.IPixelmonBankAccountManager;
import net.minecraft.entity.player.EntityPlayerMP;

public class EconomyUtils {

    private static final IPixelmonBankAccountManager manager = Pixelmon.moneyManager;

    public static void takeMoney(EntityPlayerMP player, int amount) {
        IPixelmonBankAccount account = manager.getBankAccount(player).orElse(null);
        if(account == null)
            return;
            account.changeMoney(-amount);
            account.updatePlayer();
        }

    public static void giveMoney(EntityPlayerMP player, int amount) {
        IPixelmonBankAccount account = manager.getBankAccount(player).orElse(null);
        if(account == null)
            return;
        account.changeMoney(amount);
        account.updatePlayer();

    }

    public static boolean hasEnoughMoney(EntityPlayerMP player, int amount) {
        IPixelmonBankAccount account = manager.getBankAccount(player).orElse(null);
        if(account == null)
            return false;
        return account.getMoney() >= amount;
    }
}
