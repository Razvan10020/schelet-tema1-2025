package entities.air_types;

import entities.Air;

public final class Polar extends Air {
    private static final double MAX_SCORE = 142;
    private static final double ICE_WEIGH = 0.05;

    private double iceCrystalConcentration;

    public Polar(final String name, final double mass,
                 final double humidity, final double temperature,
                 final double oxygenLevel, final double iceCrystalConcentration) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.iceCrystalConcentration = iceCrystalConcentration;
    }

    public double calculateQualityScore() {
        double score = (getOxygenLevel() * 2)
                + (getProcentageMultiplier() - Math.abs(getTemperature()))
                - (this.iceCrystalConcentration * ICE_WEIGH);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(getProcentageMultiplier(), score));
        //rotunjirea scorului
        return Math.round(normalizeScore * getRoundingFactor()) / getRoundingFactor();
    }

    @Override
    public double getMaxScore() {
        return MAX_SCORE;
    }
}
