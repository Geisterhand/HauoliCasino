package eu.mccluster.hauolicasino.timer;

import ca.landonjw.gooeylibs2.implementation.tasks.Task;
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
import eu.mccluster.hauolicasino.utils.TimeUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LotteryTimer {

    public static void timer() {
        AtomicInteger reminder = new AtomicInteger();
        Task.builder()
                .execute(() -> {

                    long remainingTime = 0;
                    if(HauoliCasino._currentLottery.size() > 0) {
                        remainingTime = new Date().getTime() - HauoliCasino._currentLottery.get(0).getDate().getTime();
                    }

                    if(HauoliCasino._currentLottery.size() == 0) {
                        short pokemonIndexNumber = GenLotteryPokemon.genPokemonNationalNumber();
                        EnumGrowth size = GenLotteryPokemon.genPokemonSize();
                        EnumNature nature = GenLotteryPokemon.genPokemonNature();
                        Gender gender = GenLotteryPokemon.genGender(pokemonIndexNumber);
                        AbilityBase ability = GenLotteryPokemon.genAbility(pokemonIndexNumber);
                        StatsType stats = GenLotteryPokemon.genStat();
                        Date date = new Date();
                        int statHeight = (int)(Math.random() * 31);
                        LotteryObject object = new LotteryObject(pokemonIndexNumber, size, nature, gender, ability, stats, statHeight, date);
                        HauoliCasino._currentLottery.add(object);
                        HauoliCasino.getData().lotteryData.add(object);
                        HauoliCasino.getData().save();
                        reminder.set(0);

                    } else if(remainingTime > 0) {
                       if (HauoliCasino._currentLottery.size() == 1 && TimeUnit.MILLISECONDS.toMinutes(remainingTime) > TimeUtils.parseCooldown(HauoliCasino.getConfig().cooldown)) {
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
                            reminder.set(0);
                            if(HauoliCasino.getConfig().bcSettings.broadcastLottery) {
                                TextUtils.broadcast(HauoliCasino.getConfig().bcSettings.lotteryMessage);
                            }
                        } else if(HauoliCasino.getConfig().bcSettings.broadcastReminder) {
                           reminder.set(reminder.get() + 1);
                           if(reminder.get() >= HauoliCasino.getConfig().bcSettings.reminderInterval * 2) {
                               TextUtils.broadcast(Placeholder.currentPokemon(Placeholder.remainingTime(HauoliCasino.getConfig().bcSettings.reminderMessage)));
                               reminder.set(0);
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
