package entities.animal_types;

import entities.Animal;
import lombok.Getter;

public class Omnivore extends Animal {
    @Getter
    private static final int ATTACK_CHANCE = 60;

    public Omnivore(final String name, final double mass) {
        super(name, mass, ATTACK_CHANCE);
    }
}
