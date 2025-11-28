package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public final class GrasslandSoil extends Soil {
    private static final double NITRO_WEIGH = 1.3;
    private static final double ORG_MAT_WEIGH = 1.5;
    private static final double ROOT_DEN_WEIGH = 0.8;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;

    @Getter @Setter
    private double rootDensity;

    public GrasslandSoil(final String name, final double mass,
                         final double nitrogen, final double waterRetention,
                         final double solidpH, final double organicMatter,
                         final double rootDensity) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.rootDensity = rootDensity;
    }

    @Override
    public double calculateQualityScore() {
        double score = (this.getNitrogen() * NITRO_WEIGH)
                + (getOrganicMatter() * ORG_MAT_WEIGH)
                + (this.rootDensity * ROOT_DEN_WEIGH);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    @Override
    public double getDamageScore() {
        double score = ((50 - rootDensity) + getWaterRetention() * 0.5) / 75 * 100;
        return score;
    }
}