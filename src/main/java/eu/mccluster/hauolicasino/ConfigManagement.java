package eu.mccluster.hauolicasino;

import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigManagement {

    private final Path MAIN_PATH = Paths.get(HauoliCasinoMod.getInstance().getDataFolder() + File.separator);

    public Path getPluginPath(String filename) {
        return Paths.get(MAIN_PATH + File.separator + filename);
    }

    public Path getPluginFolder() {
        Path folderPath = MAIN_PATH;
        if(Files.notExists(folderPath)) {
            try {
                Files.createDirectory(folderPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return folderPath;
    }

    private void ConfigManager() {
        if(Files.notExists(MAIN_PATH)) {
            try {
                Files.createDirectory(MAIN_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public <T> T loadConfig(Class<T> clazz, Path path) {
        final YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(path)
                .nodeStyle(NodeStyle.BLOCK)
                .build();

        try {

            CommentedConfigurationNode node = loader.load();
            T config = node.get(clazz);

            node.set(clazz, config);
            loader.save(node);
            return config;

        } catch(ConfigurateException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> void saveConfig(T config, Path path) {
        final YamlConfigurationLoader loader = YamlConfigurationLoader.builder()
                .path(path)
                .nodeStyle(NodeStyle.BLOCK)
                .build();

        try {
            CommentedConfigurationNode node = loader.load();
            node.set(config.getClass(), config);
            loader.save(node);
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    private static ConfigManagement INSTANCE;

    /**
     * @return the ConfigManager instance
     */
    public static ConfigManagement getInstance() {
        if(INSTANCE == null)
            INSTANCE = new ConfigManagement();
        return INSTANCE;
    }

}
