package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public final class ForestSoil extends Soil {
    private static final double NITRO_WEIGH = 1.2;
    private static final double ORG_MAT_WEIGH = 2;
    private static final double H2O_RET_WEIGH = 1.5;
    private static final double LEAF_LIT_WEIGH = 0.3;
    @Getter @Setter
    private double leafLitter;

    public ForestSoil(final String name, final double mass,
                      final double nitrogen, final double waterRetention,
                      final double solidpH, final double organicMatter,
                      final double leafLitter) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.leafLitter = leafLitter;
    }

    @Override
    public double SoilQuality() {
        double score = (this.getNitrogen() * NITRO_WEIGH)
                + (this.getOrganicMatter() * ORG_MAT_WEIGH)
                + (this.getWaterRetention() * H2O_RET_WEIGH)
                + (this.leafLitter * LEAF_LIT_WEIGH);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
    }

    private static final double P_H2O_RET_WEIGH = 0.6;
    private static final double P_LEAF_LIT_WEIGH = 0.4;
    private static final double PROCENT = 80;

    public double PossibilityToGetStuckInSoil() {
        return (this.getWaterRetention() * P_H2O_RET_WEIGH + this.leafLitter * P_LEAF_LIT_WEIGH)
                / PROCENT * getProcentageMultiplier();
    }
}
