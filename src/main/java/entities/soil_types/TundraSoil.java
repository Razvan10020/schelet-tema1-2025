package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public final class TundraSoil extends Soil {
    private static final double NITRO_WEIGH = 0.7;
    private static final double ORG_MAT_WEIGH = 0.5;
    private static final double PEM_FROST_WEIGH = 1.5;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;

    @Getter @Setter
    private double permafrostDepth;

    public TundraSoil(final String name, final double mass,
                      final double nitrogen, final double waterRetention,
                      final double solidpH, final double organicMatter,
                      final double permafrostDepth) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.permafrostDepth = permafrostDepth;
    }

    @Override
    public double calculateQualityScore() {
        double score = (getNitrogen() * NITRO_WEIGH)
                + (getOrganicMatter() * ORG_MAT_WEIGH)
                - (this.permafrostDepth * PEM_FROST_WEIGH);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    private static final int PERMAFROST_DEPTH_THRESHOLD = 50;
    private static final int DIVISOR_FOR_SCORE = 50;
    @Override
    public double getDamageScore() {
        double score = (PERMAFROST_DEPTH_THRESHOLD - permafrostDepth)
                / DIVISOR_FOR_SCORE * PROCENTAGE_MULTIPLIER;
        return score;
    }
}
