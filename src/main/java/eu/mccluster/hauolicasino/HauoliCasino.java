package eu.mccluster.hauolicasino;

import eu.mccluster.hauolicasino.commands.CommandRegistry;
import eu.mccluster.hauolicasino.config.LangConfig;
import eu.mccluster.hauolicasino.config.LootTableStart;
import eu.mccluster.hauolicasino.config.MainConfig;
import eu.mccluster.hauolicasino.config.Data;
import eu.mccluster.hauolicasino.objects.LotteryObject;
import eu.mccluster.hauolicasino.timer.LotteryTimer;
import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HauoliCasino {

    @Getter
    private static LootTableStart _loot;

    @Getter
    private static MainConfig _config;

    @Getter
    private static LangConfig _lang;

    @Getter
    private static Data _data;

    @Getter
    private static HauoliCasino _instance;

    @Getter
    private static ConcurrentHashMap<UUID, List<ItemStack>> _playerLoot = new ConcurrentHashMap<>();

    public static List<LotteryObject> _currentLottery = new ArrayList<>();

    @Getter
    private final String _lootFolder = HauoliCasinoMod.getInstance().getDataFolder() + File.separator + "loottables" + File.separator;

    public static void load() {
        if(_instance == null) {
            _instance = new HauoliCasino();
        }
    }

    public static void enable(FMLServerStartingEvent event){
        _instance.onEnable(event);
    }

    public static void started() { _instance.onStarted(); }


    private void onEnable(FMLServerStartingEvent event) {
        _loot = new LootTableStart(new File(HauoliCasinoMod.getInstance().getDataFolder(), "Loottable.conf"));
        _config = new MainConfig(new File(HauoliCasinoMod.getInstance().getDataFolder(), "HauoliLottery.conf"));
        _lang = new LangConfig(new File(HauoliCasinoMod.getInstance().getDataFolder(), "Lang.conf"));
        _data = new Data(new File(HauoliCasinoMod.getInstance().getDataFolder(), "Data.conf"));
        CommandRegistry.registerCommands(event);
        _instance.onReload();
        if(_data.lotteryData.size() == 1) {
            _currentLottery.add(_data.lotteryData.get(0));
        }
    }

    private void onStarted() {
        LotteryTimer.timer();
    }

    public void onReload() {
        _loot.load();
        _config.load();
        _lang.load();
        _data.load();

    }

}
