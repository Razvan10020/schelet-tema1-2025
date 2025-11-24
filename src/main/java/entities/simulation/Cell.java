package entities.simulation;

import entities.Air;
import entities.Animal;
import entities.Plant;
import entities.Soil;
import entities.Water;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single cell in the territory grid.
 */
@Getter
@Setter
public class Cell {
    private Soil soil;
    private Air air;
    private Plant plant;
    private Water water;
    private Animal animal;

    /**
     * Constructor for the Cell class.
     */
    public Cell() {
        this.soil = null;
        this.air = null;
        this.plant = null;
        this.water = null;
        this.animal = null;
    }

    /**
     * Gets the number of entities in the cell.
     * @return The number of entities.
     */
    public int getNumberOfEntities() {
        int count = 0;
        if (plant != null) {
            count++;
        }
        if (animal != null) {
            count++;
        }
        if (water != null) {
            count++;
        }
        return count;
    }
}