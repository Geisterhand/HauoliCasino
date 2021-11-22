package eu.mccluster.hauolicasino.timer;

import ca.landonjw.gooeylibs2.implementation.tasks.Task;
import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import eu.mccluster.hauolicasino.ConfigManagement;
import eu.mccluster.hauolicasino.HauoliCasino;
import eu.mccluster.hauolicasino.config.pokelottery.Data;
import eu.mccluster.hauolicasino.config.pokelottery.PokeLotteryConfig;
import eu.mccluster.hauolicasino.objects.LotteryObject;
import eu.mccluster.hauolicasino.utils.GenLotteryPokemon;
import eu.mccluster.hauolicasino.utils.Placeholder;
import eu.mccluster.hauolicasino.utils.TextUtils;
import eu.mccluster.hauolicasino.utils.TimeUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class LotteryTimer {

    public static void timer() {

        PokeLotteryConfig config = ConfigManagement.getInstance().loadConfig(PokeLotteryConfig.class, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "PokeLottery.yml"));

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
                        Data data = ConfigManagement.getInstance().loadConfig(Data.class, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "Data.yml"));
                        data.lotteryData.add(object);
                        ConfigManagement.getInstance().saveConfig(data, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "Data.yml"));
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
                           Data data = ConfigManagement.getInstance().loadConfig(Data.class, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "Data.yml"));

                            data.lotteryData.set(0, object);
                            data.playerList.clear();
                           ConfigManagement.getInstance().saveConfig(data, Paths.get(HauoliCasino.LOTTERY_PATH + File.separator + "Data.yml"));
                            reminder.set(0);
                            if(config.bcSettings.broadcastLottery) {
                                TextUtils.broadcast(Placeholder.currentPokemon(Placeholder.remainingTime(Placeholder.currentIdentifiers(config.bcSettings.lotteryMessage))));
                            }
                        } else if(config.bcSettings.broadcastReminder) {
                           reminder.set(reminder.get() + 1);
                           if(reminder.get() >= config.bcSettings.reminderInterval * 2) {
                               TextUtils.broadcast(Placeholder.currentPokemon(Placeholder.remainingTime(Placeholder.currentIdentifiers(config.bcSettings.reminderMessage))));
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
