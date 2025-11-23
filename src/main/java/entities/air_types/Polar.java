package entities.air_types;

import entities.Air;
import lombok.Getter;
import lombok.Setter;

public class Polar extends Air {
    @Getter @Setter
    private double iceCrystalConcentration;

    public Polar(String name, double mass, double humidity, double temperature,double oxygenLevel, double iceCrystalConcentration) {
        super(name, mass, humidity, temperature, oxygenLevel);
        this.iceCrystalConcentration = iceCrystalConcentration;
    }

    public double calculateQualityScore(){
        double score = (getOxygenLevel()*2) + (100-Math.abs(getTemperature())) - (this.iceCrystalConcentration*0.05);
        // normalizarea scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }
}
