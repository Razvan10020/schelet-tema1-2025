package entities.simulation;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the territory grid.
 */
@Getter
@Setter
public class Territory {
    private Cell[][] grid;
    private int rows;
    private int cols;
    private int currentTime;

    /**
     * Constructor for the Territory class.
     * @param rows The number of rows in the territory.
     * @param cols The number of columns in the territory.
     */
    public Territory(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
        this.currentTime = 1;
    }

    /**
     * Gets the cell at the specified coordinates.
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return The cell at the specified coordinates.
     */
    public Cell getCell(final int x, final int y) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            return grid[x][y];
        }
        return null;
    }
}