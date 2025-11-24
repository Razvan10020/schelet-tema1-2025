package entities;

import lombok.Getter;

public class Animal extends Entity {
    @Getter
    private double attackChance;

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
