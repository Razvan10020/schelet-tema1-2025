package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public final class ForestSoil extends Soil {
    private static final double NITRO_WEIGH = 1.2;
    private static final double ORG_MAT_WEIGH = 2;
    private static final double H2O_RET_WEIGH = 1.5;
    private static final double LEAF_LIT_WEIGH = 0.3;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;

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
    public double calculateQualityScore() {
        double score = (this.getNitrogen() * NITRO_WEIGH)
                + (this.getOrganicMatter() * ORG_MAT_WEIGH)
                + (this.getWaterRetention() * H2O_RET_WEIGH)
                + (this.leafLitter * LEAF_LIT_WEIGH);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    private static final double RET_WEIGH = 0.6;
    private static final double LEAF_LIT_SCORE = 0.4;
    private static final int DIVISOR_FOR_SCORE = 80;
    @Override
    public double getDamageScore() {
        double score = (getWaterRetention() * RET_WEIGH + leafLitter * LEAF_LIT_SCORE)
                / DIVISOR_FOR_SCORE * PROCENTAGE_MULTIPLIER;
        return score;
    }
}
