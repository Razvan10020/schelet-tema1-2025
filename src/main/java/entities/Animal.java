package entities;

import lombok.Getter;
import lombok.Setter;

public class Animal extends Entity {
    @Getter
    private double attackChance;
    @Getter @Setter
    private boolean scanned = false;
    @Getter @Setter
    private AnimalState state;
    @Getter @Setter
    private int moveCounter;
    @Getter @Setter
    private int x;
    @Getter @Setter
    private int y;

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
        this.state = AnimalState.HUNGRY;
        this.moveCounter = 0;
    }
}
