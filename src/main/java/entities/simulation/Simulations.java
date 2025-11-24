package entities.simulation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entities.Air;
import entities.Animal;
import entities.Plant;
import entities.Soil;
import entities.TerraBot;
import entities.Water;
import entities.air_types.DesertAir;
import entities.air_types.MountainAir;
import entities.air_types.PolarAir;
import entities.air_types.TemperatAir;
import entities.air_types.TropicalAir;
import entities.soil_types.DesertSoil;
import entities.soil_types.ForestSoil;
import entities.soil_types.GrasslandSoil;
import entities.soil_types.SwampSoil;
import entities.soil_types.TundraSoil;
import fileio.SimulationInput;
import fileio.WaterInput;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    public ObjectNode printEnvConditions() {
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

            if (soil instanceof ForestSoil) {
                soilNode.put("leafLitter", ((ForestSoil) soil).getLeafLitter());
            } else if (soil instanceof DesertSoil) {
                soilNode.put("salinity", ((DesertSoil) soil).getSalinity());
            } else if (soil instanceof GrasslandSoil) {
                soilNode.put("rootDensity", ((GrasslandSoil) soil).getRootDensity());
            } else if (soil instanceof SwampSoil) {
                soilNode.put("waterLogging", ((SwampSoil) soil).getWaterLogging());
            } else if (soil instanceof TundraSoil) {
                soilNode.put("permafrostDepth", ((TundraSoil) soil).getPermafrostDepth());
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
            if (air instanceof TemperatAir) {
                airNode.put("pollenLevel", ((TemperatAir) air).getPollenLevel());
            } else if (air instanceof DesertAir) {
                airNode.put("dustParticles", ((DesertAir) air).getDustParticles());
            } else if (air instanceof MountainAir) {
                airNode.put("altitude", ((MountainAir) air).getAltitude());
            } else if (air instanceof PolarAir) {
                airNode.put("iceCrystalConcentration", ((PolarAir) air).getIceCrystalConcentration());
            } else if (air instanceof TropicalAir) {
                airNode.put("co2Level", ((TropicalAir) air).getCo2Level());
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
                Cell cell = territory.getCell(j, i);
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
}
