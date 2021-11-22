package eu.mccluster.hauolicasino.config.scratchcard;


import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class ScratchCardConfig {

    @Comment("Default Loottable for Scratchcard")
    public List<ScratchcardData> lootData = new ArrayList<>();

    public ScratchCardConfig() {

        lootData.add(new ScratchcardData());
    }
}
