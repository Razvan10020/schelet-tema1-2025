package entities.air_types;

import entities.Air;

public final class DesertAir extends Air {
    private static final double MAX_SCORE = 65;
    private static final double FACTOR_FOR_DUST_PARTICLE = 0.2;
    private static final double FACTOR_FOR_TEMP = 0.3;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;

    private double dustParticles;


    public double getDustParticles() {
        return dustParticles;
    }

    public DesertAir(final String name, final double mass,
                     final double humidity, final double temperature,
                     final double oxygenLevel, final double dustParticles) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.dustParticles = dustParticles;
    }

    @Override
    public double calculateQualityScore() {
        double score = (getOxygenLevel() * 2)
                - (this.dustParticles * FACTOR_FOR_DUST_PARTICLE)
                - (getTemperature() * FACTOR_FOR_TEMP);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
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