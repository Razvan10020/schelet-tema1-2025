package entities;

import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.abs;

public final class Water extends Entity {
    private static final double PH_SCORE_FACTOR = 7.5;
    private static final double SALINITY_SCORE_FACTOR = 350;

    private static final double PS_FACTOR = 0.3;
    private static final double PHS_FACTOR = 0.2;
    private static final double SS_FACTOR = 0.15;
    private static final double TS_FACTOR = 0.1;
    private static final double CS_FACTOR = 0.2;
    private static final double FS_FACTOR = 0.2;

    @Getter
    private double salinity;
    @Getter
    private double pH;
    @Getter
    private double purity;
    @Getter
    private double turbidity;
    @Getter
    private double contaminantIndex;
    @Getter
    private boolean isFrozen;
    @Getter @Setter
    private boolean scanned = false;
    @Getter @Setter
    private int interactionCounter = 0;

    /**
     * Increments the interaction counter.
     */
    public void incrementInteractionCounter() {
        this.interactionCounter++;
    }

    public Water(final String name, final double mass,
                  final double salinity, final double ph,
                  final double purity, final double turbidity,
                  final double contaminantIndex, final boolean isFrozen) {
        super(name, mass);
        this.salinity           = salinity;
        this.pH                 = ph;
        this.purity             = purity;
        this.turbidity          = turbidity;
        this.contaminantIndex   = contaminantIndex;
        this.isFrozen           = isFrozen;
    }

    /**
     *
     */
    public double calculateQuality() {
        double purityScore        = purity / getProcentageMultiplier();
        double pHScore            = 1 - abs(pH - PH_SCORE_FACTOR) / PH_SCORE_FACTOR;
        double salinityScore      = 1 - (salinity / SALINITY_SCORE_FACTOR);
        double turbidityScore     = 1 - (turbidity / getProcentageMultiplier());
        double contaminantScore   = 1 - (contaminantIndex / getProcentageMultiplier());
        double frozenScore        = isFrozen ? 0 : 1;

        return (PS_FACTOR * purityScore
                             + PHS_FACTOR * pHScore
                             + SS_FACTOR * salinityScore
                             + TS_FACTOR * turbidityScore
                             + CS_FACTOR * contaminantScore
                             + FS_FACTOR * frozenScore)
                             * getProcentageMultiplier();
    }
}
