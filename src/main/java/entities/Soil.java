package entities;

public abstract class Soil extends Entity {
    private double nitrogen;
    private double waterRetention;
    private double soilpH;
    private double organicMatter;

    public Soil(final String name, final double mass, final double nitrogen,
                final double waterRetention, final double soilpH,
                final double organicMatter) {
        super(name, mass);
        this.nitrogen = nitrogen;
        this.waterRetention = waterRetention;
        this.soilpH = soilpH;
        this.organicMatter = organicMatter;
    }

    public abstract double PossibilityToGetStuckInSoil();
    public abstract double SoilQuality();

    public double getNitrogen() {
        return nitrogen;
    }

    public double getWaterRetention() {
        return waterRetention;
    }

    public double getSoilpH() {
        return soilpH;
    }

    public double getOrganicMatter() {
        return organicMatter;
    }
}
