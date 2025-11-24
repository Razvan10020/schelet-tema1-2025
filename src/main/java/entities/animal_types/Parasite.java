package entities.animal_types;

import entities.Animal;
import lombok.Getter;

public class Parasite extends Animal {
    @Getter
    private static final int ATTACK_CHANCE = 10;

    public Parasite(final String name, final double mass) {
        super(name, mass, ATTACK_CHANCE);
    }
}
