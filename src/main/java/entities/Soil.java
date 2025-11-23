package entities;

import lombok.Getter;
import lombok.Setter;

public abstract class Soil extends Entity {
    @Getter
    private double nitrogen;
    @Getter
    private double waterRetention;
    @Getter
    private double soilpH;
    @Getter
    private double organicMatter;

    public Soil(final String name, double mass, double nitrogen, double waterRetention, double soilpH, double organicMatter) {
        super(name, mass);
        this.nitrogen = nitrogen;
        this.waterRetention = waterRetention;
        this.soilpH = soilpH;
        this.organicMatter = organicMatter;
    }

    public abstract double PossibilityToGetStuckInSoil();
    public abstract double SoilQuality();
}
