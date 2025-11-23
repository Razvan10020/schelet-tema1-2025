package entities;

import lombok.Getter;

public abstract class Plant extends Entity {
    @Getter
    private String status;
    @Getter
    private double growthRate;
    @Getter
    private double oxygenFromPlant;
    @Getter
    private double possibilityToGetStuck;

    public Plant (String name, double mass, double oxygenFromPlant, double possibilityToGetStuck) {
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
            }
        }
        return this.status;
    }

    public double getMaturityOxigenRate() {
        return switch (getStatus()) {
            case "young" -> 0.2;
            case "mature" -> 0.7;
            case "old" -> 0.4;
            default -> 0.0;
        };
    }

    public double getTotalOxygenFromPlant(){
        return this.oxygenFromPlant + getMaturityOxigenRate();
    }

    public double PossibiltyToGetStuckInPlants() {
        return this.possibilityToGetStuck;
    }

}
