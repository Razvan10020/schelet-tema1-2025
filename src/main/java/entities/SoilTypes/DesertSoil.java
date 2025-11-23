package entities.SoilTypes;

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
    public double calculateQualityScore(){
        double score = (this.getNitrogen() * 0.5) + (this.getWaterRetention() * 0.3) - (salinity * 2);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }
}
