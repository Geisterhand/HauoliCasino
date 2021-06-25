package eu.mccluster.hauolicasino.utils;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.config.PixelmonItems;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import com.pixelmonmod.pixelmon.enums.EnumSpecies;
import eu.mccluster.hauolicasino.HauoliCasino;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class GenLotteryPokemon {

    public static short genPokemonNationalNumber() {
        return Short.parseShort(EnumSpecies.randomPoke(HauoliCasino.getConfig().legendaryLottery).getNationalPokedexNumber());
    }

    public static Pokemon genPokemon(short nationaldexNumber) {
        EnumSpecies species = EnumSpecies.getFromDex(nationaldexNumber);
        return Pixelmon.pokemonFactory.create(species);
    }


    public static ItemStack genPokemonSprite(short nationaldexNumber) {
        ItemStack sprite = new ItemStack(PixelmonItems.itemPixelmonSprite, 1);
        NBTTagCompound tagCompound = new NBTTagCompound();
        sprite.setTagCompound(tagCompound);
        tagCompound.setShort("ndex", nationaldexNumber);
        return sprite;
    }

    public static String genPokemonName(short nationaldexNumber) {
        return EnumSpecies.getFromDex(nationaldexNumber).getPokemonName();
    }

    public static EnumGrowth genPokemonSize() {
        return EnumGrowth.getRandomGrowth();
    }

    public static EnumNature genPokemonNature() {
        return EnumNature.getRandomNature();
    }

    public static Gender genGender(short nationaldexNumber) {
        EnumSpecies species = EnumSpecies.getFromDex(nationaldexNumber);
        Pokemon pokemon = Pixelmon.pokemonFactory.create(species);
        return Gender.getRandomGender(pokemon.getBaseStats());
    }

    public static AbilityBase genAbility(short nationaldexNumber) {
        EnumSpecies species = EnumSpecies.getFromDex(nationaldexNumber);
        Pokemon pokemon = Pixelmon.pokemonFactory.create(species);
        return pokemon.getAbility();
    }

    public static StatsType genStat() {
     int pickedIV = (int) (6 * Math.random());
     switch(pickedIV) {
         case 1:
             return StatsType.HP;
         case 2:
             return StatsType.Attack;
         case 3:
             return StatsType.Defence;
         case 4:
             return StatsType.SpecialAttack;
         case 5:
             return StatsType.SpecialDefence;
         default:
             return StatsType.Speed;
     }
    }

}
