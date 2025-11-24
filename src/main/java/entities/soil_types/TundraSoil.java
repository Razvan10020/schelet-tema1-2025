package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

import java.time.Period;

public final class TundraSoil extends Soil {
    private static final double NITRO_WEIGH = 0.7;
    private static final double ORG_MAT_WEIGH = 0.5;
    private static final double PEM_FROST_WEIGH = 1.5;
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
    public double SoilQuality() {
        double score = 	(getNitrogen() * NITRO_WEIGH)
                + (getOrganicMatter() * ORG_MAT_WEIGH)
                - (this.permafrostDepth * PEM_FROST_WEIGH);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
    }

    /**
     * Calculates teh possibility yo get stuck in the Tundra Soil
     * @return
     */
    private static final int FIFTY = 50;
    public double PossibilityToGetStuckInSoil() {
        return 	(FIFTY - this.permafrostDepth) / FIFTY * getProcentageMultiplier();
    }
}
