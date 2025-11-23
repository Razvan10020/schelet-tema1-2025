package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public class SwampSoil extends Soil {
    @Getter @Setter
    private double waterLogging;

    public SwampSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double waterLogging) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.waterLogging = waterLogging;
    }

    @Override
    public double SoilQuality(){
        double score = (this.getNitrogen() * 1.1) + (this.getOrganicMatter() * 2.2) - (this.waterLogging * 5);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }

    public double PossibilityToGetStuckInSoil(){
        return 	this.waterLogging * 10;
    }
}
