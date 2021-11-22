package eu.mccluster.hauolicasino.config.scratchcard;


import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.ArrayList;
import java.util.List;

@ConfigSerializable
public class ScratchCardLang {


    public String menuTitle = "§6Scratchcard";

    public String glassPanes = "§6Scratchcard";

    public String startGame = "§5Buy a Scratchcard";

    public List<String> gameInfo = new ArrayList<>();

    public String concealedCard = "§c???";

    public String blank = "§cBlank";

    public String noMoney = "§cYou don't have enough money!";

    public String unavailable = "§cBuy a scratchcard first!";

    public String freeCard = "§c§lFree Scratchcard";



    public ScratchCardLang() {
        gameInfo.add("§4Cost: §6100 Dollar");
        gameInfo.add("");
        gameInfo.add("§cPossible Loots:");
        gameInfo.add("§6- 1x Pokeball");
    }

}
