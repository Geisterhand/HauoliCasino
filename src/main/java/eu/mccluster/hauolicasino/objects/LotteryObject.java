package eu.mccluster.hauolicasino.objects;

import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import eu.mccluster.dependency.configmanager.api.Config;
import eu.mccluster.dependency.configmanager.api.annotations.Order;

import java.io.File;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

public class LotteryObject extends Config {

    @Order(1)
    private short indexNumber;

    @Order(2)
    private int growth;

    @Order(3)
    private int nature;

    @Order(4)
    private String gender;

    @Order(5)
    private String ability;

    @Order(6)
    private String stat;

    @Order(7)
    private int statHeight;

    @Order(7)
    private long lastRotation;

    public LotteryObject() {

    }

    public LotteryObject(short indexNumber, EnumGrowth growth, EnumNature nature, Gender gender, AbilityBase ability, StatsType stat, int statHeight, Date date) {
        this.indexNumber = indexNumber;
        this.growth = growth.index;
        this.nature = nature.index;
        this.gender = gender.toString();
        this.ability = ability.getName();
        this.stat = stat.toString();
        this.statHeight = statHeight;
        this.lastRotation = date.getTime();

    }

    public short getIndexNumber() { return this.indexNumber; }

    public EnumGrowth getGrowth() { return EnumGrowth.getGrowthFromIndex(this.growth); }

    public EnumNature getNature() { return EnumNature.getNatureFromIndex(this.nature); }

    public Gender getGender() { return Gender.getGender(gender); }

    public Optional<AbilityBase> getAbility() { return AbilityBase.getAbility(this.ability); }

    public StatsType getStat() { return StatsType.valueOf(stat); }

    public Integer getStatHeight() { return this.statHeight; }

    public Date getDate() { return Date.from(Instant.ofEpochMilli(this.lastRotation)); }



    @Override
    public File getFile() {
        return null;
    }
}
