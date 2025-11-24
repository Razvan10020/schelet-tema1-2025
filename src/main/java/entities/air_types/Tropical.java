package entities.air_types;

import entities.Air;

public final class Tropical extends Air {
    private static final double MAX_SCORE = 82;
    private static final double HUMIDITY_WEIGH = 0.5;
    private static final double CO2_WEIGH = 0.01;

    private double co2Level;

    public Tropical(final String name, final double mass,
                    final double humidity, final double temperature,
                    final double oxygenLevel, final double co2Level) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.co2Level = co2Level;
    }

    /**
     * Calculates the air quality without getting its toxicity
     * @return
     */
    public double calculateQualityScore() {
        double score = (this.getOxygenLevel() * 2)
                + (this.getHumidity() * HUMIDITY_WEIGH)
                - (co2Level * CO2_WEIGH);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        //rotunjirea scorului
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
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
