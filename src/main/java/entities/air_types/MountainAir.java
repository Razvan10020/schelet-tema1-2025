package entities.air_types;

import entities.Air;
import lombok.Getter;

public final class MountainAir extends Air {
    private static final double MAX_SCORE = 78;
    private static final double HUMIDITY_WEIGH = 0.6;
    private static final double ALTITUDE_WEIGH = 0.5;
    private static final double ALTITUDE_FACTOR = 1000.0;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;
    private static final double HIKER_FACTOR = 0.1;

    @Getter
    private double altitude;
    @Getter
    private int numberOfHikers;

    public MountainAir(final String name, final double mass,
                       final double humidity, final double temperature,
                       final double oxygenLevel, final double altitude) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.altitude = altitude;
    }

    @Override
    public double calculateQualityScore() {
        // Calculeaza oxygenFactor
        double oxygenFactor = getOxygenLevel() - (this.altitude / ALTITUDE_FACTOR * ALTITUDE_WEIGH);
        double score = (oxygenFactor * 2) + (getHumidity() * HUMIDITY_WEIGH);

        // Normalizare && Rotunjire
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    /**
     * Updates the wheather based on the number of hikers.
     * @param hikers The number of hikers.
     */
    public void updateWeather(final int hikers) {
        this.numberOfHikers = hikers;
        setNewQuality(getNewQuality() - (hikers * HIKER_FACTOR));
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