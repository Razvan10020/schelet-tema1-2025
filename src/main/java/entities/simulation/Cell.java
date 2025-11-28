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

    /**
     * Gets the Quality score of the cell
     * @return
     */
    private static final double PERCENTAGE_DIVISOR = 100.0;
    private static final int ANIMAL_ATTACK_DIVISOR = 10;
    private static final int ANIMAL_ATTACK_BASE = 100;
    public int getQuality() {
        double sum = 0;
        int count = 0;

        if (soil != null) {
            double score = soil.getDamageScore();
            sum += score;
            count++;
        }
        if (air != null) {
            double score = air.possibilityToGetDamagedByAir();
            sum += score;
            count++;
        }
        if (plant != null) {
            double score = plant.getPossibilityToGetStuck() / PERCENTAGE_DIVISOR;
            sum += score;
            count++;
        }
        if (animal != null) {
            double score = (ANIMAL_ATTACK_BASE - animal.getAttackChance()) / (double) ANIMAL_ATTACK_DIVISOR;
            sum += score;
            count++;
        }

        if (count == 0) {
            return 0;
        }

        double mean = Math.abs(sum / count);
        int result = (int) Math.round(mean);
        return result;
    }
}