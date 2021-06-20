package eu.mccluster.hauolilottery.utils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import eu.mccluster.hauolilottery.HauoliLottery;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LotteryUtils {

    public static boolean hasLotteryPokemon(EntityPlayerMP playerMP, Pokemon pokemon) {
        Optional<PlayerPartyStorage> partyStorageOptional = Optional.ofNullable(Pixelmon.storageManager.getParty(playerMP.getUniqueID()));
        if (!partyStorageOptional.isPresent()) {
            return false;
        }

        PlayerPartyStorage party = partyStorageOptional.get();

        for (int i = 0; i < party.getTeam().size(); i++) {
            if (party.getTeam().get(i).getTranslatedName().equals(pokemon.getTranslatedName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isLotteryPokemon(PlayerPartyStorage party, int index, Pokemon pokemon) {
        return party.getTeam().get(index).getTranslatedName().equals(pokemon.getTranslatedName());
    }

    public static List<Boolean> checkPokemon(EntityPlayerMP playerMP, Pokemon pokemon, EnumGrowth size, EnumNature nature, Gender gender, AbilityBase ability, StatsType stat) {
        List<Boolean> checks = new ArrayList<>();
        Optional<PlayerPartyStorage> partyStorageOptional = Optional.ofNullable(Pixelmon.storageManager.getParty(playerMP.getUniqueID()));
        if (!partyStorageOptional.isPresent()) {
            return checks;
        }

        PlayerPartyStorage party = partyStorageOptional.get();

        for (int i = 0; i < party.getTeam().size(); i++) {
            if (isLotteryPokemon(party, i, pokemon)) {
                checks.add(hasSameGrowth(party, size, i));
                checks.add(hasSameNature(party, nature, i));
                checks.add(hasSameGender(party, gender, i));
                checks.add(hasSameAbility(party, ability, i));
                checks.add(hasSameIV(party, stat, i));
                return checks;
            }
        }
        for (int i = 0; i < 5; i++) {
            checks.add(false);
        }
        return checks;
    }

    public static Integer equalSize(List<Boolean> booleanList) {
        int equals = 0;
        for (Boolean aBoolean : booleanList) {
            if (aBoolean) {
                equals = equals + 1;
            }
        }
        return equals;
    }


    public static boolean hasSameGrowth(PlayerPartyStorage party, EnumGrowth growth, int index) {
        return party.getTeam().get(index).getGrowth().equals(growth);

    }

    public static boolean hasSameNature(PlayerPartyStorage party, EnumNature nature, int index) {
                    return party.getTeam().get(index).getNature().equals(nature);
    }


    public static boolean hasSameGender(PlayerPartyStorage party, Gender gender, int index) {
        return party.getTeam().get(index).getGender().equals(gender);
    }

    public static boolean hasSameAbility(PlayerPartyStorage party, AbilityBase ability, int index) {
        return party.getTeam().get(index).getAbility().equals(ability);

    }

    public static boolean hasSameIV(PlayerPartyStorage party, StatsType statsType, int index) {
        int stat = party.getTeam().get(index).getIVs().getStat(statsType);
        return stat == HauoliLottery._currentLottery.get(0).getStatHeight();
    }

}
