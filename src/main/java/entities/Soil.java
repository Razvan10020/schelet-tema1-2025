package entities;

import lombok.Getter;
import lombok.Setter;

public abstract class Soil extends Entity {
    @Getter
    private double nitrogen;
    @Getter @Setter
    private double waterRetention;
    @Getter
    private double soilpH;
    @Getter
    private double organicMatter;

    public Soil(final String name, final double mass, final double nitrogen,
                final double waterRetention, final double soilpH,
                final double organicMatter) {
        super(name, mass);
        this.nitrogen = nitrogen;
        this.waterRetention = waterRetention;
        this.soilpH = soilpH;
        this.organicMatter = organicMatter;
    }

    /**
     * Abstract method that gets overridden by every soil type
     * @return
     */
    public abstract double calculateQualityScore();

    /**
     * Abstract method to calculate the possibility of getting stuck in the soil.
     * @return The possibility score.
     */
    public abstract double getDamageScore();

    private static final int GOOD_QUALITY = 70;
    private static final int MODERATE_QUALITY = 40;

    /**
     * Gets the quality of the soil.
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

    public final void updateWaterRetention() {
        this.waterRetention = this.waterRetention + 0.1;
    }
}
