package entities;

import lombok.Getter;

public abstract class Soil extends Entity {
    @Getter
    private double nitrogen;
    @Getter
    private double waterRetention;
    @Getter
    private double soilpH;
    @Getter
    private double organicMatter;

    public Soil(final String name, final double mass, final double nitrogen,
                final double waterRetention, final double soilpH,
                final double organicMatter) {
        super(name, mass);
        this.nitrogen = nitrogen;
        this.waterRetention = waterRetention;
        this.soilpH = soilpH;
        this.organicMatter = organicMatter;
    }

    /**
     * Abstract method that gets overridden by every plant type
     * @return
     */
    public abstract double soilQuality();
}
