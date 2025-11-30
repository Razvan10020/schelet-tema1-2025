package entities;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the TeraBot robot.
 */
@Getter
@Setter
public class TerraBot {
    private int x;
    private int y;
    private int energy;
    private boolean isCharging;
    private int fullEnergy;
    private int chargeUnit;

    /**
     * Constructor for the TeraBot class.
     * @param energy The initial energy of the robot.
     */
    public TerraBot(final int energy) {
        this.x = 0;
        this.y = 0;
        this.energy = energy;
        this.fullEnergy = energy;
        this.isCharging = false;
        this.chargeUnit = 0;
    }
}
