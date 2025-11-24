package entities;

import lombok.Getter;

public abstract class Plant extends Entity {
    private static final double OXY_PS_Y = 0.2;
    private static final double OXY_PS_M = 0.7;
    private static final double OXY_PS_O = 0.4;
    private static final double OXY_PS_D = 0.0;

    @Getter
    private String status;
    @Getter
    private double growthRate;
    @Getter
    private double oxygenFromPlant;
    @Getter
    private double possibilityToGetStuck;

    /**
     * Constructor for Plant entity.
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

    /**
     * returns the updates status of the plant
     * @return
     */
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

    /**
     * Gets the oxygen rate that correlates to the status of the plant
     * @return
     */
    public double getMaturityOxigenRate() {
        return switch (getStatus()) {
            case "young" -> OXY_PS_Y;
            case "mature" -> OXY_PS_M;
            case "old" -> OXY_PS_O;
            default -> OXY_PS_D;
        };
    }

    /**
     * Gets the total oxygen that a plant will generate
     * @return
     */
    public double getTotalOxygenFromPlant() {
        return this.oxygenFromPlant + getMaturityOxigenRate();
    }

    /**
     * Gets the possibility of the robot to get stuck in plants
     * @return
     */
    public double possibilityToGetStuckInPlants() {
        return this.possibilityToGetStuck;
    }
}
