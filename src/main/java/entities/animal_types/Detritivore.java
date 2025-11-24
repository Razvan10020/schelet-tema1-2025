package entities.animal_types;

import entities.Animal;
import lombok.Getter;

public class Detritivore extends Animal {
    @Getter
    private static final int ATTACK_CHANCE = 90;

    public Detritivore(final String name, final double mass) {
        super(name, mass, ATTACK_CHANCE);
    }
}
