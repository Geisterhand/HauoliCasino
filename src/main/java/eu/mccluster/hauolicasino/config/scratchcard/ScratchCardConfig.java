package eu.mccluster.hauolicasino.config.scratchcard;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Comment;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScratchCardConfig extends Config {

    @Order(1)
    @Comment("Default Loottable for PokeLottery")
    public List<ScratchcardData> lootData = new ArrayList<>();

    public ScratchCardConfig() {

        lootData.add(new ScratchcardData());
    }

    @Override
    public File getFile() {
        return null;
    }
}
