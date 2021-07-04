package eu.mccluster.hauolicasino.config.scratchcard;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;

public class ScratchCardStart extends Config {

    @Skip
    File _lootFile;

    public ScratchCardStart(File file) { _lootFile = file; }

    @Order(1)
    public ScratchCardConfig loottable = new ScratchCardConfig();

    @Override
    public File getFile() {
        return _lootFile;
    }
}
