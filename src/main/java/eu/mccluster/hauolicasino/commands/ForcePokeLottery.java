package eu.mccluster.hauolicasino.commands;

import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.objects.LotteryObject;
import eu.mccluster.hauolicasino.utils.GenLotteryPokemon;
import eu.mccluster.hauolicasino.utils.Placeholder;
import eu.mccluster.hauolicasino.utils.TextUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import java.util.Date;

public class ForcePokeLottery extends CommandBase {
    @Override
    public String getName() {
        return "forcepokelottery";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return sender.canUseCommand(4, "hauolicasino.forcelottery");
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/forcepokelottery";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        short pokemonIndexNumber = GenLotteryPokemon.genPokemonNationalNumber();
        EnumGrowth size = GenLotteryPokemon.genPokemonSize();
        EnumNature nature = GenLotteryPokemon.genPokemonNature();
        Gender gender = GenLotteryPokemon.genGender(pokemonIndexNumber);
        AbilityBase ability = GenLotteryPokemon.genAbility(pokemonIndexNumber);
        StatsType stats = GenLotteryPokemon.genStat();
        Date date = new Date();
        int statHeight = (int)(Math.random() * 31);
        LotteryObject object = new LotteryObject(pokemonIndexNumber, size, nature, gender, ability, stats, statHeight, date);
        HauoliCasino._currentLottery.set(0, object);
        HauoliCasino.getData().lotteryData.set(0, object);
        HauoliCasino.getData().playerList.clear();
        HauoliCasino.getData().save();

        if(HauoliCasino.getConfig().bcSettings.broadcastLottery) {
            TextUtils.broadcast(Placeholder.currentPokemon(Placeholder.remainingTime(Placeholder.currentIdentifiers(HauoliCasino.getConfig().bcSettings.lotteryMessage))));
        }

        sender.sendMessage(TextUtils.toText("[&dHauoliCasino&r] &4Started new PokeLottery!"));
    }
}
