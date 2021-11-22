package eu.mccluster.hauolicasino;

import eu.mccluster.hauolicasino.commands.CommandRegistry;
import eu.mccluster.hauolicasino.config.HauoliCasinoConfig;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryLangConfig;
import eu.mccluster.hauolicasino.config.pokelottery.LootTableStart;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryConfig;
import eu.mccluster.hauolicasino.config.pokelottery.Data;
import eu.mccluster.hauolicasino.config.scratchcard.ScratchCardGeneralConfig;
import eu.mccluster.hauolicasino.config.scratchcard.ScratchCardLang;
import eu.mccluster.hauolicasino.config.scratchcard.ScratchCardStart;
import eu.mccluster.hauolicasino.objects.LotteryObject;
import eu.mccluster.hauolicasino.timer.LotteryTimer;
import lombok.Getter;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private static ScratchCardStart _scratchLoot;

    @Getter
    private static ScratchCardGeneralConfig _scratchConfig;

    @Getter
    private static ScratchCardLang _scratchLang;

    @Getter
    private static HauoliCasino _instance;

    @Getter
    private static ConcurrentHashMap<UUID, List<ItemStack>> _playerLoot = new ConcurrentHashMap<>();

    public static List<LotteryObject> _currentLottery = new ArrayList<>();

    public static final Path MAIN_PATH = ConfigManagement.getInstance().getPluginFolder();
    public static final Path LOTTERY_PATH = Paths.get(MAIN_PATH + File.separator + "pokelottery");
    public static final Path SCRATCH_PATH = Paths.get(MAIN_PATH + File.separator + "scratchcard");

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
        _loot = ConfigManagement.getInstance().loadConfig(LootTableStart.class, Paths.get(LOTTERY_PATH + File.separator + "Loottable.yml"));
        _config = ConfigManagement.getInstance().loadConfig(PokeLotteryConfig.class, Paths.get(LOTTERY_PATH + File.separator + "PokeLottery.yml"));
        _lang = ConfigManagement.getInstance().loadConfig(PokeLotteryLangConfig.class, Paths.get(LOTTERY_PATH + File.separator + "Lang.yml"));
        _data = ConfigManagement.getInstance().loadConfig(Data.class, Paths.get(LOTTERY_PATH + File.separator + "Data.yml"));
        _module = ConfigManagement.getInstance().loadConfig(HauoliCasinoConfig.class, Paths.get(MAIN_PATH + File.separator + "HauoliCasino.yml"));
        _scratchLoot = ConfigManagement.getInstance().loadConfig(ScratchCardStart.class, Paths.get(SCRATCH_PATH + File.separator + "Loottable.yml"));
        _scratchConfig = ConfigManagement.getInstance().loadConfig(ScratchCardGeneralConfig.class, Paths.get(SCRATCH_PATH + File.separator + "Scratchcard.yml"));
        _scratchLang = ConfigManagement.getInstance().loadConfig(ScratchCardLang.class, Paths.get(SCRATCH_PATH + File.separator + "Lang.yml"));
        CommandRegistry.registerCommands(event);
        if(_data.lotteryData.size() == 1 && _module.modulePokeLottery) {
            _currentLottery.add(_data.lotteryData.get(0));
        }
    }

    private void onStarted() {
        LotteryTimer.timer();
    }

    public void onReload() {
        _loot = ConfigManagement.getInstance().loadConfig(LootTableStart.class, Paths.get(LOTTERY_PATH + File.separator + "Loottable.yml"));
        _config = ConfigManagement.getInstance().loadConfig(PokeLotteryConfig.class, Paths.get(LOTTERY_PATH + File.separator + "PokeLottery.yml"));
        _lang = ConfigManagement.getInstance().loadConfig(PokeLotteryLangConfig.class, Paths.get(LOTTERY_PATH + File.separator + "Lang.yml"));
        _data = ConfigManagement.getInstance().loadConfig(Data.class, Paths.get(LOTTERY_PATH + File.separator + "Data.yml"));
        _scratchLoot = ConfigManagement.getInstance().loadConfig(ScratchCardStart.class, Paths.get(SCRATCH_PATH + File.separator + "Loottable.yml"));
        _scratchConfig = ConfigManagement.getInstance().loadConfig(ScratchCardGeneralConfig.class, Paths.get(SCRATCH_PATH + File.separator + "Scratchcard.yml"));
        _scratchLang = ConfigManagement.getInstance().loadConfig(ScratchCardLang.class, Paths.get(SCRATCH_PATH + File.separator + "Lang.yml"));
    }

}
