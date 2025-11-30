package entities;

import lombok.Getter;
import lombok.Setter;

public class Animal extends Entity {
    @Getter
    private double attackChance;
    @Getter @Setter
    private boolean scanned = false;

    /**
     * Constructor for Animal entity.
     *
     * @param name the name of the air entity
     * @param mass the mass of the air entity
     * @param attackChance is the attack chance of every type
     */
    public Animal(final String name, final double mass, final double attackChance) {
        super(name, mass);
        this.attackChance = attackChance;
    }
}
