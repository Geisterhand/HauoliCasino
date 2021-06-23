package eu.mccluster.hauolicasino.utils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import eu.mccluster.hauolicasino.HauoliCasino;
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
        return party.getTeam().get(index).getUnlocalizedName().equals(pokemon.getUnlocalizedName());
    }

    public static List<Boolean> checkPokemon(EntityPlayerMP playerMP, Pokemon pokemon, EnumGrowth size, EnumNature nature, Gender gender, AbilityBase ability, StatsType stat) {
        List<Boolean> checks = new ArrayList<>();
        List<Boolean> highestChecks = new ArrayList<>();
        Optional<PlayerPartyStorage> partyStorageOptional = Optional.ofNullable(Pixelmon.storageManager.getParty(playerMP.getUniqueID()));
        if (!partyStorageOptional.isPresent()) {
            return checks;
        }

        for (int i = 0; i < 5; i++) {
            checks.add(false);
        }

        for (int i = 0; i < 5; i++) {
            highestChecks.add(false);
        }

        PlayerPartyStorage party = partyStorageOptional.get();

        int equal;
        int highestEqual = 0;

        for (int i = 0; i < party.getTeam().size(); i++) {
            if (isLotteryPokemon(party, i, pokemon)) {
                checks.set(0, hasSameGrowth(party, size, i));
                checks.set(1, hasSameNature(party, nature, i));
                checks.set(2, hasSameGender(party, gender, i));
                checks.set(3, hasSameAbility(party, ability, i));
                checks.set(4, hasSameIV(party, stat, i));
                equal = 0;
                for(int c = 0; c < 5; c++) {
                    if(checks.get(c)) {
                        equal = equal + 1;
                    }
                }
                if(equal > highestEqual) {
                    highestChecks.clear();
                    highestChecks.addAll(checks);
                    highestEqual = equal;
                }
            }
        }

        return highestChecks;
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
        return stat == HauoliCasino._currentLottery.get(0).getStatHeight();
    }

    public static boolean isOriginalTrainer(EntityPlayerMP playerMP, Pokemon pokemon) {
        Optional<PlayerPartyStorage> partyStorageOptional = Optional.ofNullable(Pixelmon.storageManager.getParty(playerMP.getUniqueID()));
        if (!partyStorageOptional.isPresent()) {
            return false;
        }

        PlayerPartyStorage party = partyStorageOptional.get();

        for (int i = 0; i < party.getTeam().size(); i++) {
            if (party.getTeam().get(i).getOriginalTrainer().equals(playerMP.getName())) {
                return true;
            }

        }
        return false;
    }

}
