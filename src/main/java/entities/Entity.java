package entities;

import lombok.Getter;

public class Entity {
    @Getter
    private final String name;
    @Getter
    private final double mass;

    private static final int PERCENTAGE_MULTIPLIER = 100;
    private static final double ROUNDING_FACTOR = 100.0;

    public Entity(final String name, final double mass) {
        this.name = name;
        this.mass = mass;
    }

    /**
     * Gets the variable equal to 100
     * @return
     */
    public int getProcentageMultiplier() {
        return PERCENTAGE_MULTIPLIER;
    }

    /**
     * Gets the variable equal to 100.0
     * @return
     */
    public double getRoundingFactor() {
        return ROUNDING_FACTOR;
    }
}
