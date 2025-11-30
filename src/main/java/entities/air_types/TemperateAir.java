package entities.air_types;

import entities.Air;
import lombok.Getter;

public final class TemperateAir extends Air {
    private static final double MAX_SCORE = 84;
    private static final double HUMIDITY_WEIGH = 0.7;
    private static final double POLLEN_WEIGH = 0.1;
    private static final int PROCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;
    private static final int SEASON_PENALTY = 15;

    @Getter
    private double pollenLevel;
    @Getter
    private String season;


    public TemperateAir(final String name, final double mass,
                        final double humidity, final double temperature,
                        final double oxygenLevel, final double pollenLevel) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.pollenLevel = pollenLevel;
    }

    /**
     * Calculates the air quality without getting its toxicity
     * @return
     */
    public double calculateQualityScore() {
        double score = (getOxygenLevel() * 2)
                + (getHumidity() * HUMIDITY_WEIGH)
                - (this.pollenLevel * POLLEN_WEIGH);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(PROCENTAGE_MULTIPLIER, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * ROUNDING_FACTOR) / ROUNDING_FACTOR;
    }

    /**
     * Applies the effect of a new season.
     * @param newSeason The new season.
     */
    public void applyNewSeason(final String newSeason) {
        this.season = newSeason;
        int seasonPenalty = "Spring".equalsIgnoreCase(newSeason) ? SEASON_PENALTY : 0;
        setNewQuality(getNewQuality() - seasonPenalty);
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
