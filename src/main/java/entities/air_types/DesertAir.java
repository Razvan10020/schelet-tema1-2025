package entities.air_types;

import entities.Air;
import lombok.Getter;

public final class DesertAir extends Air {
    private static final double MAX_SCORE = 65;
    private static final double FACTOR_FOR_DUST_PARTICLE = 0.2;
    private static final double FACTOR_FOR_TEMP = 0.3;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;
    @Getter
    private double dustParticles;

    private static final int STORM_DUST_INCREASE = 150;

    public DesertAir(final String name, final double mass,
                     final double humidity, final double temperature,
                     final double oxygenLevel, final double dustParticles) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.dustParticles = dustParticles;
    }

    @Override
    public double calculateQualityScore() {
        double currentDustParticles = this.dustParticles;
        if (desertStorm) {
            currentDustParticles += STORM_DUST_INCREASE;
        }
        double score = (getOxygenLevel() * 2)
                - (currentDustParticles * FACTOR_FOR_DUST_PARTICLE)
                - (getTemperature() * FACTOR_FOR_TEMP);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    private boolean desertStorm;
    private int stormStartTimestamp = -1;
    private static final int STORM_DURATION = 2;

    public boolean isDesertStorm() {
        return desertStorm;
    }


    /**
     * Sets the desert storm status.
     * @param isStorming The desert storm status.
     * @param currentTimestamp The current timestamp.
     */
    public void setDesertStorm(final boolean isStorming, final int currentTimestamp) {
        this.desertStorm = isStorming;
        if (isStorming) {
            this.stormStartTimestamp = currentTimestamp;
        }
    }
    /**
     * Updates the storm status based on the current timestamp.
     * @param currentTimestamp The current timestamp.
     */
    public void updateStorm(final int currentTimestamp) {
        if (stormStartTimestamp != -1 && currentTimestamp >= stormStartTimestamp + STORM_DURATION) {
            this.desertStorm = false;
            setWheatherChanged(false);
            this.stormStartTimestamp = -1;
        }
    }

    /**
     * Gets the max score of said subclass so it can be sent to Air class
     * @return
     */
    @Override
    public double getMaxScore() {
        return MAX_SCORE;
    }
}
