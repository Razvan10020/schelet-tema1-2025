package entities.air_types;

import entities.Air;

public final class Montan extends Air {
    private static final double MAX_SCORE = 78;
    private static final double HUMIDITY_WEIGH = 0.6;
    private static final double ALTITUDE_WEIGH = 0.5;
    private static final double ALTITUDE_FACTOR = 1000.0;

    private double altitude;

    public Montan(final String name, final double mass,
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
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
    }

    @Override
    public double getMaxScore() {
        return MAX_SCORE;
    }
}
