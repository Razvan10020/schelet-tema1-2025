package entities.SoilTypes;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public class ForestSoil extends Soil {
    @Getter @Setter
    private double leafLitter;

    public ForestSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double leafLitter) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.leafLitter = leafLitter;
    }

    @Override
    public double calculateQualityScore(){
        double score = (this.getNitrogen() * 1.2) + (this.getOrganicMatter() * 2) + (this.getWaterRetention() * 1.5) + (leafLitter * 0.3);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }
}
