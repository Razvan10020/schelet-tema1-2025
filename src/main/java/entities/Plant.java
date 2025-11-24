package entities;

import lombok.Getter;

public abstract class Plant extends Entity {
    private static final double OXY_PS_Y = 0.2;
    private static final double OXY_PS_M = 0.7;
    private static final double OXY_PS_O = 0.4;
    private static final double OXY_PS_D = 0.0;

    @Getter
    private String status;
    private double growthRate;
    private double oxygenFromPlant;
    private double possibilityToGetStuck;

    /**
     * Constructor for Air entity.
     *
     * @param name the name of the air entity
     * @param mass the mass of the air entity
     * @param oxygenFromPlant the humidity level
     * @param possibilityToGetStuck the temperature
     */
    public Plant(final String name, final double mass,
                 final double oxygenFromPlant, final double possibilityToGetStuck) {
        super(name, mass);
        this.status = "young";
        this.growthRate = 0.0;
        this.oxygenFromPlant = oxygenFromPlant;
        this.possibilityToGetStuck = possibilityToGetStuck;
    }

    public String updateMaturity() {
        if (this.growthRate >= 1.0) {
            this.growthRate = 0.0;

            switch (this.status) {
                case "young":
                    this.status = "mature";
                    break;
                case "mature":
                    this.status = "old";
                    break;
                case "old":
                    this.status = "dead";
                    break;
                default:
                    this.status = "dead";
            }
        }
        return this.status;
    }

    public double getMaturityOxigenRate() {
        return switch (getStatus()) {
            case "young" -> OXY_PS_Y;
            case "mature" -> OXY_PS_M;
            case "old" -> OXY_PS_O;
            default -> OXY_PS_D;
        };
    }

    public double getTotalOxygenFromPlant() {
        return this.oxygenFromPlant + getMaturityOxigenRate();
    }

    public double PossibilityToGetStuckInPlants() {
        return this.possibilityToGetStuck;
    }
}
