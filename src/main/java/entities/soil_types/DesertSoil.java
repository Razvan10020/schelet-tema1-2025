package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public final class DesertSoil extends Soil {
    private static final double NITRO_WEIGH = 0.5;
    private static final double H20_RET_WEIGH = 0.3;

    @Getter @Setter
    private double salinity;

    public DesertSoil(final String name, final double mass,
                      final double nitrogen, final double waterRetention,
                      final double solidpH, final double organicMatter,
                      final double salinity) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.salinity = salinity;
    }


    @Override
    public double SoilQuality() {
        double score = (this.getNitrogen() * NITRO_WEIGH)
                + (this.getWaterRetention() * H20_RET_WEIGH)
                - (this.salinity * 2);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
    }

    public double PossibilityToGetStuckInSoil() {
        return (getProcentageMultiplier() - this.getWaterRetention() + this.salinity) / getProcentageMultiplier() * getRoundingFactor();
    }
}
