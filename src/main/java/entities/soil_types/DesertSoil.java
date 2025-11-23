package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public class DesertSoil extends Soil {
    @Getter @Setter
    private double salinity;

    public DesertSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter,  double salinity) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.salinity = salinity;
    }


    @Override
    public double SoilQuality(){
        double score = (this.getNitrogen() * 0.5) + (this.getWaterRetention() * 0.3) - (this.salinity * 2);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }

    public double PossibilityToGetStuckInSoil(){
        return (100 - this.getWaterRetention() + this.salinity) / 100 * 100;
    }
}
