package entities;

import lombok.Getter;
import lombok.Setter;

public abstract class Air extends Entity {
    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;
    private static final double TOXICITY_THRESHOLD = 0.8;
    @Getter @Setter
    private double humidity;
    @Getter @Setter
    private double temperature;
    @Getter @Setter
    private double oxygenLevel;

    /**
     * Constructor for Air entity.
     *
     * @param name the name of the air entity
     * @param mass the mass of the air entity
     * @param humidity the humidity level
     * @param temperature the temperature
     * @param oxygenLevel the oxygen level
     */
    public Air(final String name, final double mass,
                final double humidity, final double temperature,
                final double oxygenLevel) {
        super(name, mass);
        this.humidity = humidity;
        this.temperature = temperature;
        this.oxygenLevel = oxygenLevel;
    }

    /**
     * Gets the maximum score for air quality calculation.
     *
     * @return the maximum score
     */
    public abstract double getMaxScore();

    /**
     * Calculates the possibility of getting damaged by air.
     *
     * @return the damage possibility as a percentage
     */
    public final double possibilityToGetDamagedByAir() {
        double airQualityScore = calculateQualityScore();
        double maxScore = getMaxScore();

        // Calculam toxicitatea reala
        double toxicityAQ = PERCENTAGE_MULTIPLIER * (1 - airQualityScore / maxScore);

        // Normalizare si Rotunjire
        double finalResult = Math.round(toxicityAQ * ROUNDING_FACTOR) / ROUNDING_FACTOR;
        return Math.max(0, finalResult);
    }

    /**
     * Checks if the air is toxic.
     *
     * @return true if the air is toxic, false otherwise
     */
    public final boolean isToxic() {
        double toxicityAQ = possibilityToGetDamagedByAir();
        double maxScore = getMaxScore();

        return toxicityAQ > (TOXICITY_THRESHOLD * maxScore);
    }

    /**
     * Calculates the quality score of the air.
     *
     * @return the quality score
     */
    public abstract double calculateQualityScore();

    /**
     * Gets the humidity level.
     *
     * @return the humidity level
     */
    public final double getHumidity() {
        return humidity;
    }

    private static final int GOOD_QUALITY = 70;
    private static final int MODERATE_QUALITY = 40;

    /**
     * Gets the quality of the air.
     * @return "good", "moderate", or "poor"
     */
    public final String getQuality() {
        double score = calculateQualityScore();
        if (score >= GOOD_QUALITY) {
            return "good";
        } else if (score >= MODERATE_QUALITY) {
            return "moderate";
        } else {
            return "poor";
        }
    }
}
