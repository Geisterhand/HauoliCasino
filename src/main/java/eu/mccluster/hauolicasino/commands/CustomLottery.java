package eu.mccluster.hauolicasino.commands;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import eu.mccluster.hauolicasino.ConfigManagement;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.config.pokelottery.Data;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryConfig;
import eu.mccluster.hauolicasino.objects.LotteryObject;
import eu.mccluster.hauolicasino.utils.Placeholder;
import eu.mccluster.hauolicasino.utils.TextUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

public class CustomLottery extends CommandBase {

    @Override
    public String getName() {
        return "customlottery";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(4, "hauolicasino.customlottery");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/customlottery";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        EntityPlayerMP player = (EntityPlayerMP) sender;

        Optional<PlayerPartyStorage> partyStorageOptional = Optional.ofNullable(Pixelmon.storageManager.getParty(player));
        if(!partyStorageOptional.isPresent()) {
            player.sendMessage(TextUtils.toText("[&dHauoliCasino&r] &4Something went wrong! Check your team."));
        } else {
            PlayerPartyStorage party = partyStorageOptional.get();
            int index = Integer.parseInt(args[0]);
            if(args[0] == null) {
                index = 1;
            }

            Pokemon pokemon = party.getTeam().get(index);
            short pokemonIndexNumber = Short.parseShort(EnumSpecies.getFromName(pokemon.getDisplayName()).get().getNationalPokedexNumber());
            EnumGrowth size = pokemon.getGrowth();
            EnumNature nature = pokemon.getNature();
            Gender gender= pokemon.getGender();
            AbilityBase ability = pokemon.getAbility();

            String statType = args[1].toUpperCase();
            StatsType stats;
            switch(statType) {
                case "HP":
                    stats = StatsType.HP;
                    break;
                case "ATK":
                    stats = StatsType.Attack;
                    break;
                case "DEF":
                    stats = StatsType.Defence;
                    break;
                case "SPA":
                    stats = StatsType.SpecialAttack;
                    break;
                case "SPD":
                    stats = StatsType.SpecialDefence;
                    break;
                case "SPE":
                    stats = StatsType.Speed;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + statType);
            }

            int statHeight = pokemon.getIVs().getStat(stats);

            Date date = new Date();

            LotteryObject object = new LotteryObject(pokemonIndexNumber, size, nature, gender, ability, stats, statHeight, date);
            HauoliCasino._currentLottery.set(0, object);

            Data data = ConfigManagement.getInstance().loadConfig(Data.class, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "Data.yml"));
            data.lotteryData.set(0, object);
            data.playerList.clear();
            ConfigManagement.getInstance().saveConfig(data, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "Data.yml"));

            PokeLotteryConfig config = ConfigManagement.getInstance().loadConfig(PokeLotteryConfig.class, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "PokeLottery.yml"));
            if(config.bcSettings.broadcastLottery) {
                TextUtils.broadcast(Placeholder.currentPokemon(Placeholder.remainingTime(Placeholder.currentIdentifiers(HauoliCasino.getConfig().bcSettings.lotteryMessage))));
            }

            sender.sendMessage(TextUtils.toText("[&dHauoliCasino&r] &4Started new PokeLottery!"));

        }
    }
}
