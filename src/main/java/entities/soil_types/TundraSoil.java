package entities.soil_types;

import entities.Soil;
import lombok.Getter;
import lombok.Setter;

public class TundraSoil extends Soil {
    @Getter @Setter
    private double permafrostDepth;

    public TundraSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double permafrostDepth) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.permafrostDepth = permafrostDepth;
    }

    @Override
    public double SoilQuality(){
        double score = 	(getNitrogen() * 0.7) + (getOrganicMatter() * 0.5) - (this.permafrostDepth * 1.5);

        //normalizare a scorului
        double normalizeScore = Math.max(0, Math.min(100, score));
        //rotunjirea si returnarea scorului
        return Math.round(normalizeScore * 100.0) / 100.0;
    }

    public double PossibilityToGetStuckInSoil(){
        return 	(50 - this.permafrostDepth) / 50 * 100;
    }
}
