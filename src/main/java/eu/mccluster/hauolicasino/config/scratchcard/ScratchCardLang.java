package eu.mccluster.hauolicasino.config.scratchcard;

import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Order;
import eu.mccluster.dependency.configmanager.api.annotations.Skip;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScratchCardLang  extends Config {

    @Skip
    File _langFile;

    @Order(1)
    public String menuTitle = "§6Scratchcard";

    @Order(2)
    public String glassPanes = "§6Scratchcard";

    @Order(2)
    public String startGame = "§5Buy a Scratchcard";

    @Order(3)
    public List<String> gameInfo = new ArrayList<>();

    @Order(4)
    public String concealedCard = "§c???";

    @Order(5)
    public String blank = "§cBlank";

    @Order(6)
    public String noMoney = "§cYou don't have enough money!";

    @Order(7)
    public String unavailable = "§cBuy a scratchcard first!";



    public ScratchCardLang(File file) {
        _langFile = file;
        gameInfo.add("§4Cost: §6100 Dollar");
        gameInfo.add("");
        gameInfo.add("§cPossible Loots:");
        gameInfo.add("§6- 1x Pokeball");
    }


    @Override
    public File getFile() {
        return _langFile;
    }
}
