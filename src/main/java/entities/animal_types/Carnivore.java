package entities.animal_types;

import entities.Animal;
import lombok.Getter;

public class Carnivore extends Animal {
    @Getter
    private static final int ATTACK_CHANCE = 85;

    public Carnivore(final String name, final double mass) {
        super(name, mass, ATTACK_CHANCE);
    }
}
