package eu.mccluster.hauolicasino;

import eu.mccluster.hauolicasino.commands.CommandRegistry;
import eu.mccluster.hauolicasino.config.HauoliCasinoConfig;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryLangConfig;
import eu.mccluster.hauolicasino.config.pokelottery.LootTableStart;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryConfig;
import eu.mccluster.hauolicasino.config.pokelottery.Data;
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
    private static PokeLotteryConfig _config;

    @Getter
    private static PokeLotteryLangConfig _lang;

    @Getter
    private static Data _data;

    @Getter
    private static HauoliCasinoConfig _module;

    @Getter
    private static HauoliCasino _instance;

    @Getter
    private static ConcurrentHashMap<UUID, List<ItemStack>> _playerLoot = new ConcurrentHashMap<>();

    public static List<LotteryObject> _currentLottery = new ArrayList<>();

    @Getter
    private final String _pokeLotteryFolder = HauoliCasinoMod.getInstance().getDataFolder() + File.separator + "pokelottery" + File.separator;

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
        _loot = new LootTableStart(new File(_pokeLotteryFolder, "Loottable.conf"));
        _config = new PokeLotteryConfig(new File(_pokeLotteryFolder, "PokeLottery.conf"));
        _lang = new PokeLotteryLangConfig(new File(_pokeLotteryFolder, "Lang.conf"));
        _data = new Data(new File(_pokeLotteryFolder, "Data.conf"));
        _module = new HauoliCasinoConfig(new File(HauoliCasinoMod.getInstance().getDataFolder(), "HauoliCasino.conf"));
        CommandRegistry.registerCommands(event);
        _instance.onReload();
        _module.load();
        if(_data.lotteryData.size() == 1 && _module.modulePokeLottery) {
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
