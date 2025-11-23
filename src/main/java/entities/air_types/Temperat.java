package entities.air_types;

import entities.Air;
import lombok.Getter;
import lombok.Setter;

public class Temperat extends Air {
    @Getter @Setter
    private double pollenLevel;
    private final static double maxScore = 84;


    public Temperat(String name, double mass, double humidity, double temperature,double oxygenLevel, double pollenLevel) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.pollenLevel = pollenLevel;
    }

    public double calculateQualityScore(){
        double score = (getOxygenLevel()*2) + (getHumidity()*0.7) - (this.pollenLevel*0.1);
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
