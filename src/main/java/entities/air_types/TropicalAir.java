package entities.air_types;

import entities.Air;
import lombok.Getter;
import lombok.Setter;

public final class TropicalAir extends Air {
    private static final double MAX_SCORE = 82;
    private static final double HUMIDITY_WEIGH = 0.5;
    private static final double CO2_WEIGH = 0.01;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;

    @Getter
    private double co2Level;
    @Getter @Setter
    private double rainfall;

    public TropicalAir(final String name, final double mass,
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
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    public void applyRainfall(double rainfall) {
        this.rainfall = rainfall;
        this.setWheatherChanged(true);
        setNewQuality(getNewQuality() + (rainfall * 0.3));
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