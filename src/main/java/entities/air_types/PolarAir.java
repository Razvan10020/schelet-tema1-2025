package entities.air_types;

import entities.Air;
import lombok.Getter;

public final class PolarAir extends Air {
    private static final double MAX_SCORE = 142;
    private static final double ICE_WEIGH = 0.05;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;
    private static final double WIND_SPEED_FACTOR = 0.2;

    @Getter
    private double iceCrystalConcentration;
    @Getter
    private double windSpeed;

    public PolarAir(final String name, final double mass,
                    final double humidity, final double temperature,
                    final double oxygenLevel, final double iceCrystalConcentration) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.iceCrystalConcentration = iceCrystalConcentration;
    }

    /**
     * Calculates the air quality without getting its toxicity
     * @return
     */
    public double calculateQualityScore() {
        double score = (getOxygenLevel() * 2)
                + (PROCENTAGE_MULTIPLIER - Math.abs(getTemperature()))
                - (this.iceCrystalConcentration * ICE_WEIGH);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    /**
     * Applies the effect of a polar storm.
     * @param speed The wind speed of the storm.
     */
    public void applyPolarStorm(final double speed) {
        this.windSpeed = speed;
        setNewQuality(getNewQuality() - (speed * WIND_SPEED_FACTOR));
        setWheatherChanged(true);
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