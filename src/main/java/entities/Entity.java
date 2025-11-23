package entities;

import lombok.Getter;
import lombok.Setter;

public abstract class Entity {
    @Getter
    private final String name;
    @Getter @Setter
    private double mass;

    public Entity(final String name, double mass) {
        this.name = name;
        this.mass = mass;
    }

    public abstract double calculateQualityScore();
}
