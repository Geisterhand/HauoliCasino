package eu.mccluster.hauolilottery.timer;

import ca.landonjw.gooeylibs2.implementation.tasks.Task;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import eu.mccluster.hauolilottery.HauoliLottery;
import eu.mccluster.hauolilottery.objects.LotteryObject;
import eu.mccluster.hauolilottery.utils.GenLotteryPokemon;
import eu.mccluster.hauolilottery.utils.TextUtils;
import eu.mccluster.hauolilottery.utils.TimeUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LotteryTimer {

    public static void timer() {
        Task.builder()
                .execute(() -> {

                    long remainingTime = 0;
                    if(HauoliLottery._currentLottery.size() > 0) {
                        remainingTime = new Date().getTime() - HauoliLottery._currentLottery.get(0).getDate().getTime();
                    }

                    if(HauoliLottery._currentLottery.size() == 0) {
                        short pokemonIndexNumber = GenLotteryPokemon.genPokemonNationalNumber();
                        EnumGrowth size = GenLotteryPokemon.genPokemonSize();
                        EnumNature nature = GenLotteryPokemon.genPokemonNature();
                        Gender gender = GenLotteryPokemon.genGender(pokemonIndexNumber);
                        AbilityBase ability = GenLotteryPokemon.genAbility(pokemonIndexNumber);
                        StatsType stats = GenLotteryPokemon.genStat();
                        Date date = new Date();
                        int statHeight = (int)(Math.random() * 31);
                        LotteryObject object = new LotteryObject(pokemonIndexNumber, size, nature, gender, ability, stats, statHeight, date);
                        HauoliLottery._currentLottery.add(object);
                        HauoliLottery.getData().lotteryData.add(object);
                        HauoliLottery.getData().save();

                    } else if(remainingTime > 0) {
                       if (HauoliLottery._currentLottery.size() == 1 && TimeUnit.MILLISECONDS.toMinutes(remainingTime) > TimeUtils.parseCooldown(HauoliLottery.getConfig().cooldown)) {
                            short pokemonIndexNumber = GenLotteryPokemon.genPokemonNationalNumber();
                            EnumGrowth size = GenLotteryPokemon.genPokemonSize();
                            EnumNature nature = GenLotteryPokemon.genPokemonNature();
                            Gender gender = GenLotteryPokemon.genGender(pokemonIndexNumber);
                            AbilityBase ability = GenLotteryPokemon.genAbility(pokemonIndexNumber);
                            StatsType stats = GenLotteryPokemon.genStat();
                            Date date = new Date();
                           int statHeight = (int)(Math.random() * 31);
                            LotteryObject object = new LotteryObject(pokemonIndexNumber, size, nature, gender, ability, stats, statHeight, date);
                            HauoliLottery._currentLottery.set(0, object);
                            HauoliLottery.getData().lotteryData.set(0, object);
                            HauoliLottery.getData().playerList.clear();
                            HauoliLottery.getData().save();
                            if(HauoliLottery.getConfig().bcSettings.broadcastLottery) {
                                TextUtils.broadcast(HauoliLottery.getConfig().bcSettings.lotteryMessage);
                            }

                        }
                    }
                })
                .infinite()
                .interval(600)
                .delay(20)
                .build();
    }

}
