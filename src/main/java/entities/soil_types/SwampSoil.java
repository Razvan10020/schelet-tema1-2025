package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public final class SwampSoil extends Soil {
    private static final double NITRO_WEIGH = 1.1;
    private static final double ORG_MAT_WEIGH = 2.2;
    private static final int H2O_LOGG_WEIGH = 5;

    @Getter @Setter
    private double waterLogging;

    public SwampSoil(final String name, final double mass,
                     final double nitrogen, final double waterRetention,
                     final double solidpH, final double organicMatter,
                     final double waterLogging) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.waterLogging = waterLogging;
    }

    @Override
    public double soilQuality() {
        double score = (this.getNitrogen() * NITRO_WEIGH)
                + (this.getOrganicMatter() * ORG_MAT_WEIGH)
                - (this.waterLogging * H2O_LOGG_WEIGH);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
    }

}
