package eu.mccluster.hauolicasino.objects;

import com.pixelmonmod.pixelmon.entities.pixelmon.abilities.AbilityBase;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.Gender;
import com.pixelmonmod.pixelmon.entities.pixelmon.stats.StatsType;
import com.pixelmonmod.pixelmon.enums.EnumGrowth;
import com.pixelmonmod.pixelmon.enums.EnumNature;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@ConfigSerializable
public class LotteryObject {

    private short indexNumber = 0;

    private int growth = 0;

    private int nature = 0;

    private String gender = "";

    private String ability = "";

    private String stat = "";

    private int statHeight = 0;

    private long lastRotation = 0;

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

}
