package eu.mccluster.hauolicasino.config;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class HauoliCasinoConfig extends Config {

    @Skip
    File _moduleFile;

    public HauoliCasinoConfig(File file) { _moduleFile = file; }


    @Order(1)
    @Comment("Toggles if the Pokelottery module should be active. Needs a restart after change")
    public boolean modulePokeLottery = true;


    @Override
    public File getFile() {
        return _moduleFile;
    }
}
