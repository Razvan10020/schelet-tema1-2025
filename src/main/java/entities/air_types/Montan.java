package entities.air_types;

import entities.Air;
import lombok.Getter;
import lombok.Setter;

public class Montan extends Air {
    @Getter @Setter
    private double altitude;

    public Montan(String name, double mass, double humidity, double temperature,double oxygenLevel, double altitude) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.altitude = altitude;
    }

    @Override
    public double calculateQualityScore() {
        // Calculeaza oxygenFactor
        double oxygenFactor = getOxygenLevel() - (this.altitude / 1000.0 * 0.5);
        double score = (oxygenFactor * 2) + (getHumidity() * 0.6);

        // Normalizare && Rotunjire
        double normalizeScore = Math.max(0, Math.min(100, score));
        return Math.round(normalizeScore * 100.0) / 100.0;
    }
}
