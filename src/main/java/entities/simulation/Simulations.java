package entities.simulation;
//verificare123
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entities.Air;
import entities.Animal;
import entities.Plant;
import entities.Soil;
import entities.TerraBot;
import entities.Water;
import entities.air_types.*;
import entities.soil_types.DesertSoil;
import entities.soil_types.ForestSoil;
import entities.soil_types.GrasslandSoil;
import entities.soil_types.SwampSoil;
import entities.soil_types.TundraSoil;
import fileio.CommandInput;
import fileio.SimulationInput;
import fileio.WaterInput;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Manages the overall simulation state.
 */
@Getter
@Setter
public class Simulations {
    private Territory territory;
    private TerraBot teraBot;
    private boolean simulationStarted;
    private SimulationInput simulationInput;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * Constructor for the Simulations class.
     */
    public Simulations() {
        this.simulationStarted = false;
    }

    /**
     * Starts a new simulation.
     * @param simulationInput The input data for the simulation.
     */
    public void startSimulation(final SimulationInput simulationInput) {
        this.simulationInput = simulationInput;
        String[] dims = simulationInput.getTerritoryDim().split("x");
        int rows = Integer.parseInt(dims[0]);
        int cols = Integer.parseInt(dims[1]);
        this.territory = new Territory(rows, cols);
        this.teraBot = new TerraBot(simulationInput.getEnergyPoints());
        this.simulationStarted = true;
    }

    /**
     * Ends the current simulation.
     */
    public void endSimulation() {
        this.simulationStarted = false;
    }
    /**
     * Prints the environmental conditions of the cell where the robot is currently located.
     * @return An ObjectNode containing the environmental conditions.
     */
    public ObjectNode printEnvConditions(){

        Cell currentCell = territory.getCell(teraBot.getX(), teraBot.getY());
        ObjectNode cellNode = MAPPER.createObjectNode();

        Soil soil = currentCell.getSoil();
        if (soil != null) {
            ObjectNode soilNode = MAPPER.createObjectNode();
            soilNode.put("type", soil.getClass().getSimpleName());
            soilNode.put("name", soil.getName());
            soilNode.put("mass", soil.getMass());
            soilNode.put("nitrogen", soil.getNitrogen());
            soilNode.put("waterRetention", soil.getWaterRetention());
            soilNode.put("soilpH", soil.getSoilpH());
            soilNode.put("organicMatter", soil.getOrganicMatter());
            soilNode.put("soilQuality", soil.calculateQualityScore());

            switch (soil) {
                case ForestSoil forestSoil -> soilNode.put("leafLitter", forestSoil.getLeafLitter());
                case DesertSoil desertSoil -> soilNode.put("salinity", desertSoil.getSalinity());
                case GrasslandSoil grasslandSoil -> soilNode.put("rootDensity", grasslandSoil.getRootDensity());
                case SwampSoil swampSoil -> soilNode.put("waterLogging", swampSoil.getWaterLogging());
                case TundraSoil tundraSoil -> soilNode.put("permafrostDepth", tundraSoil.getPermafrostDepth());
                default -> {
                }
            }
            cellNode.set("soil", soilNode);
        }

        if (currentCell.getPlant() != null) {
            ObjectNode plantNode = MAPPER.createObjectNode();
            Plant plant = currentCell.getPlant();
            plantNode.put("type", plant.getClass().getSimpleName());
            plantNode.put("name", plant.getName());
            plantNode.put("mass", plant.getMass());
            cellNode.set("plants", plantNode);
        }
        if (currentCell.getAnimal() != null) {
            ObjectNode animalNode = MAPPER.createObjectNode();
            Animal animal = currentCell.getAnimal();

            String type = animal.getClass().getSimpleName();
            if (!type.endsWith("s")) {
                type += "s";
            }
            animalNode.put("type", type);
            animalNode.put("name", animal.getName());
            animalNode.put("mass", animal.getMass());
            cellNode.set("animals", animalNode);
        }
        if (currentCell.getWater() != null) {
            ObjectNode waterNode = MAPPER.createObjectNode();
            Water water = currentCell.getWater();
            for (WaterInput waterInput : simulationInput.getTerritorySectionParams().getWater()) {
                if (waterInput.getName().equals(water.getName())) {
                    waterNode.put("type", waterInput.getType());
                    break;
                }
            }
            waterNode.put("name", water.getName());
            waterNode.put("mass", water.getMass());
            cellNode.set("water", waterNode);
        }

        Air air = currentCell.getAir();
        if (air != null) {
            ObjectNode airNode = MAPPER.createObjectNode();
            airNode.put("type", air.getClass().getSimpleName());
            airNode.put("name", air.getName());
            airNode.put("mass", air.getMass());
            airNode.put("humidity", air.getHumidity());
            airNode.put("temperature", air.getTemperature());
            airNode.put("oxygenLevel", air.getOxygenLevel());
            airNode.put("airQuality", air.calculateQualityScore());
            switch (air) {
                case TemperateAir a -> airNode.put("pollenLevel", a.getPollenLevel());

                case DesertAir a -> airNode.put("desertStorm", a.isDesertStorm());

                case MountainAir a -> {
                    airNode.put("altitude", a.getAltitude());
                }

                case PolarAir a -> {
                    airNode.put("iceCrystalConcentration", a.getIceCrystalConcentration());
                }

                case TropicalAir a -> {
                    airNode.put("co2Level", a.getCo2Level());
                }

                default -> {
                    // nu facem nimic pentru alte tipuri
                }
            }

            cellNode.set("air", airNode);
        }
        return cellNode;
    }

    /**
     * Prints a summary of each cell on the map.
     * @return An ArrayNode containing the map summary.
     */
    public ArrayNode printMap() {
        ArrayNode mapNode = MAPPER.createArrayNode();
        List<ObjectNode> cellNodes = new ArrayList<>();

        for (int i = 0; i < territory.getRows(); i++) {
            for (int j = 0; j < territory.getCols(); j++) {
                Cell cell = territory.getCell(i, j);
                ObjectNode cellInfo = MAPPER.createObjectNode();
                ArrayNode section = MAPPER.createArrayNode();
                section.add(j);
                section.add(i);
                cellInfo.set("section", section);
                cellInfo.put("totalNrOfObjects", cell.getNumberOfEntities());
                if (cell.getAir() != null) {
                    cellInfo.put("airQuality", cell.getAir().getQuality());
                }
                if (cell.getSoil() != null) {
                    cellInfo.put("soilQuality", cell.getSoil().getQuality());
                }
                cellNodes.add(cellInfo);
            }
        }
        for (ObjectNode node : cellNodes) {
            mapNode.add(node);
        }

        return mapNode;
    }

    public String moveRobot() {

        int x = teraBot.getX();
        int y = teraBot.getY();

        int bestScore = Integer.MAX_VALUE;
        int bestX = -1;
        int bestY = -1;

        // Order: up, right, down, left
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < territory.getCols() && ny >= 0 && ny < territory.getRows()) {
                Cell neighborCell = territory.getCell(nx, ny);
                if (neighborCell != null) {
                    int currentScore = neighborCell.getQuality();
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestX = nx;
                        bestY = ny;
                    }
                }
            }
        }

        if (bestX != -1) { // A valid destination was found
            if (teraBot.getEnergy() >= bestScore) {
                teraBot.setEnergy(teraBot.getEnergy() - bestScore);
                teraBot.setX(bestX);
                teraBot.setY(bestY);
                return "The robot has successfully moved to position (" + bestY + ", " + bestX + ").";
            } else {
                return "ERROR: Not enough battery left. Cannot perform action";
            }
        }

        return "ERROR: Cannot move to any adjacent cell.";
    }

    public String getEnergyStatus(){
        if(teraBot.isCharging()){
            return "ERROR: Robot still charging. Cannot perform action";
        }
        int x = teraBot.getEnergy();
        return "TerraBot has " + x + " energy points left.";
    }

    public String rechargeBattery(int timeToChange, int timestep){
        if(teraBot.isCharging()){
            return "ERROR: Robot still charging. Cannot perform action";
        }
        if(teraBot.getEnergy() < teraBot.getFull_energy()){
            teraBot.setCharging(true);
            teraBot.setCharge_unit(timestep + timeToChange);
            teraBot.setEnergy(teraBot.getEnergy() + timeToChange);
            return "Robot battery is charging.";
        }
        else return "Robot is fully charged";
    }

    public String changeWeatherConditions(CommandInput command) {
        for (int i = 0; i < territory.getRows(); i++) {
            for (int j = 0; j < territory.getCols(); j++) {

                Air air = territory.getCell(i, j).getAir();
                if (air != null) {

                    switch (command.getType()) {
                        case "rainfall":
                            if (air instanceof TropicalAir) ((TropicalAir) air)
                                    .applyRainfall(command.getRainfall());
                            break;

                        case "polarStorm":
                            if (air instanceof PolarAir) ((PolarAir) air)
                                    .applyPolarStorm(command.getWindSpeed());
                            break;

                        case "newSeason":
                            if (air instanceof TemperateAir) ((TemperateAir) air).applyNewSeason(command.getSeason());
                            break;

                        case "desertStorm":
                            if (air instanceof DesertAir) ((DesertAir) air).setDesertStorm(
                                        command.isDesertStorm(),
                                        command.getTimestamp()
                                );
                            break;

                        case "peopleHiking":
                            if (air instanceof MountainAir) ((MountainAir) air).updateWeather(
                                        command.getNumberOfHikers()
                                );
                            break;
                    }
                }
            }
        }
        return "The weather has changed.";
    }

    public void plantEnvChange(int x, int y){

        Cell currentCell = territory.getCell(x,y);
        currentCell.getPlant().updateMaturity();
        double last_oxygen_level = currentCell.getAir().getOxygenLevel();
        double adaos = currentCell.getPlant().getTotalOxygenFromPlant();
        double result = last_oxygen_level + adaos;
        result = Math.round(result * 100.0) / 100.0;
        currentCell.getAir().setOxygenLevel(result);
    }

    public void advanceTime(int newTime) {
        int currentTime = territory.getCurrentTime();
        while (currentTime < newTime) {
            currentTime++;
            for (int i = 0; i < territory.getRows(); i++) {
                for (int j = 0; j < territory.getCols(); j++) {
                    Cell cell = territory.getCell(i, j);
                    if (cell.getPlant() != null && cell.getPlant().isScanned()) {
                        plantEnvChange(i, j);
                    }
                    if (cell.getWater() != null && cell.getWater().isScanned()) {
                        cell.getWater().incrementInteractionCounter();
                        if (cell.getWater().getInteractionCounter() >= 2 && cell.getWater().getInteractionCounter() % 2 == 0) {
                            if (cell.getAir() != null) {
                                double newHumidity = cell.getAir().getHumidity() + 0.1;
                                newHumidity = Math.round(newHumidity * 100.0) / 100.0;
                                cell.getAir().setHumidity(newHumidity);
                            }
                            if (cell.getSoil() != null) {
                                double newWaterRetention = cell.getSoil().getWaterRetention() + 0.1;
                                newWaterRetention = Math.round(newWaterRetention * 100.0) / 100.0;
                                cell.getSoil().setWaterRetention(newWaterRetention);
                            }
                        }
                    }
                    if (cell.getAir() instanceof DesertAir) {
                        ((DesertAir) cell.getAir()).updateStorm(currentTime);
                    }
                }
            }
        }
        territory.setCurrentTime(newTime);
    }

    private static final int CONSTANT_OF_L_ENERGY = 7;
    public String scanObject(CommandInput command) {
        teraBot.setEnergy(teraBot.getEnergy() - CONSTANT_OF_L_ENERGY);

        String scannedColor = command.getColor();
        String scannedSmell = command.getSmell();
        String scannedSound = command.getSound();

        if(Objects.equals(scannedColor, "none")
        && Objects.equals(scannedSmell, "none")
        && Objects.equals(scannedSound, "none")) {
            territory.getCell(teraBot.getX(), teraBot.getY()).getWater().setScanned(true);
            return "The scanned object is water.";
        }
        if(!Objects.equals(scannedColor, "none")
                && !Objects.equals(scannedSmell, "none")
                && Objects.equals(scannedSound, "none")) {
            territory.getCell(teraBot.getX(), teraBot.getY()).getPlant().setScanned(true);
            return "The scanned object is a plant.";
        }
        else if (!Objects.equals(scannedColor, "none")
                && !Objects.equals(scannedSmell, "none")
                && !Objects.equals(scannedSound, "none")) {
            territory.getCell(teraBot.getX(), teraBot.getY()).getAnimal().setScanned(true);
            return "The scanned object is a animal.";
        }
        return " ";
    }

}
