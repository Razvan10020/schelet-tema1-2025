package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public class GrasslandSoil extends Soil {
    @Getter @Setter
    private double rootDensity;

    public GrasslandSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double rootDensity) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.rootDensity = rootDensity;
    }

    @Override
    public double calculateQualityScore(){
        double score = (this.getNitrogen() * 1.3) + (getOrganicMatter() * 1.5) + (this.rootDensity * 0.8);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }
}
