package eu.mccluster.hauolicasino.config;


import org.spongepowered.configurate.objectmapping.ConfigSerializable;
import org.spongepowered.configurate.objectmapping.meta.Comment;


@ConfigSerializable
public class HauoliCasinoConfig{

    @Comment("Toggles if the Pokelottery module should be active. Needs a restart after change")
    public boolean modulePokeLottery = true;

    @Comment("Toggles if the Scratchcard module should be active. Needs a restart after change")
    public boolean moduleScratchCard = true;

}
