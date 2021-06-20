package eu.mccluster.hauolilottery;


import eu.mccluster.dependency.deploader.api.DependencyLoader;
import eu.mccluster.dependency.deploader.api.DependencyLoaderApi;
import lombok.Getter;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(
        modid = "hauolilottery",
        name = "HauoliLottery",
        version = "@VERSION@",
        acceptableRemoteVersions = "*"
)
public class HauoliLotteryMod {

    private Logger logger;

    @Getter
    private static HauoliLotteryMod _instance;

    @Getter
    private File _dataFolder;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        _dataFolder = new File(event.getModConfigurationDirectory(), "hauolilottery");
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        _instance = this;
        initDependencies();
        HauoliLottery.load();
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        HauoliLottery.enable(event);
        logger.info("Started HauoliLottery!");
    }

    @Mod.EventHandler
    public void started(FMLServerStartedEvent event) {
        HauoliLottery.started();
    }

    private void initDependencies() {
        final DependencyLoaderApi depLoader = DependencyLoader.getInstance(this);
        depLoader.loadDependency("eu.mccluster.dependency:configmanager-dependency:1.1");

    }
}