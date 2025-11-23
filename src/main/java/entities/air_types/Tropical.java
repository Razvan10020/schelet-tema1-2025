package entities.air_types;

import entities.Air;
import lombok.Getter;
import lombok.Setter;

public class Tropical extends Air {
    @Getter @Setter
    private double co2Level;
    private final static double maxScore = 82;


    public Tropical(String name, double mass, double humidity, double temperature,double oxygenLevel, double co2Level) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.co2Level = co2Level;
    }

    public double calculateQualityScore(){
        double score = (this.getOxygenLevel()*2) + (this.getHumidity()*0.5) - (co2Level*0.01);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }

    @Override
    public double getMaxScore(){
        return maxScore;
    }
}
