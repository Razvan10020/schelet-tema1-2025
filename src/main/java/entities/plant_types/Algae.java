package entities.plant_types;

import entities.Plant;
import lombok.Getter;

public class Algae extends Plant {
    @Getter
    private static final double OXYGEN_BASE = 0.5;
    @Getter
    private static final double POSSIBILITY_TO_BLOCK = 20.0;

    public Algae(final String name, final double mass) {
        super(name, mass, OXYGEN_BASE, POSSIBILITY_TO_BLOCK);
    }
}
