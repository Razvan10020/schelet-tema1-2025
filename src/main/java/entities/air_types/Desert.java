package entities.AirTypes;

import entities.Air;
import lombok.Getter;
import lombok.Setter;

public class Desert extends Air {
    @Getter @Setter
    private double dustParticles;

    public Tropical(String name, double mass, double humidity, double temperature, double oxygenLevel, double dustParticles) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.dustParticles = dustParticles;
    }

    public double calculateQualityScore(){
        double score = (getOxygenLevel()*2) - (this.dustParticles*0.2) - (getTemperature()*0.3);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }
}
