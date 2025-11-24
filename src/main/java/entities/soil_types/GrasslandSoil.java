package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public final class GrasslandSoil extends Soil {
    private static final double NITRO_WEIGH = 1.3;
    private static final double ORG_MAT_WEIGH = 1.5;
    private static final double ROOT_DEN_WEIGH = 0.8;

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
    public double SoilQuality() {
        double score = (this.getNitrogen() * NITRO_WEIGH)
                + (getOrganicMatter() * ORG_MAT_WEIGH)
                + (this.rootDensity * ROOT_DEN_WEIGH);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
    }

    private static final int SEVEN_FIVE = 75;
    private static final int FIFTY = 50;
    private static final double H2O_RET_WEIGH = 0.5;
    public double PossibilityToGetStuckInSoil() {
        return ((FIFTY - this.rootDensity) + this.getWaterRetention() * H2O_RET_WEIGH)
                / SEVEN_FIVE * getProcentageMultiplier();
    }
}
