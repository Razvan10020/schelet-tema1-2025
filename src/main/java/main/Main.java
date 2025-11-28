package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import entities.Air;
import entities.Animal;
import entities.Plant;
import entities.Soil;
import entities.Water;
import entities.air_types.TemperateAir;
import entities.air_types.DesertAir;
import entities.air_types.MountainAir;
import entities.air_types.PolarAir;
import entities.air_types.TropicalAir;
import entities.animal_types.Carnivore;
import entities.animal_types.Detritivore;
import entities.animal_types.Herbivore;
import entities.animal_types.Omnivore;
import entities.animal_types.Parasite;
import entities.plant_types.Algae;
import entities.plant_types.Ferns;
import entities.plant_types.FloweringPlants;
import entities.plant_types.GymnospermsPlants;
import entities.plant_types.Mosses;
import entities.simulation.Simulations;
import entities.soil_types.DesertSoil;
import entities.soil_types.ForestSoil;
import entities.soil_types.GrasslandSoil;
import entities.soil_types.SwampSoil;
import entities.soil_types.TundraSoil;
import fileio.AirInput;
import fileio.AnimalInput;
import fileio.CommandInput;
import fileio.InputLoader;
import fileio.PlantInput;
import fileio.PairInput;
import fileio.SimulationInput;
import fileio.SoilInput;
import fileio.WaterInput;

import java.io.File;
import java.io.IOException;

/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {

    private Main() {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final ObjectWriter WRITER = MAPPER.writer().withDefaultPrettyPrinter();

    private static void initializeSimulation(final Simulations simulations,
                                             final SimulationInput currentSimulation) {
        for (SoilInput soilData : currentSimulation.getTerritorySectionParams().getSoil()) {
            String name = soilData.getName();
            String type = soilData.getType();

            Soil soilEntity = null;
            switch (type) {
                case "ForestSoil":
                    soilEntity = new ForestSoil(
                            name,
                            soilData.getMass(),
                            soilData.getNitrogen(),
                            soilData.getWaterRetention(),
                            soilData.getSoilpH(),
                            soilData.getOrganicMatter(),
                            soilData.getLeafLitter()
                    );
                    break;
                case "DesertSoil":
                    soilEntity = new DesertSoil(
                            name,
                            soilData.getMass(),
                            soilData.getNitrogen(),
                            soilData.getWaterRetention(),
                            soilData.getSoilpH(),
                            soilData.getOrganicMatter(),
                            soilData.getSalinity()
                    );
                    break;
                case "GrasslandSoil":
                    soilEntity = new GrasslandSoil(
                            name,
                            soilData.getMass(),
                            soilData.getNitrogen(),
                            soilData.getWaterRetention(),
                            soilData.getSoilpH(),
                            soilData.getOrganicMatter(),
                            soilData.getRootDensity()
                    );
                    break;
                case "SwampSoil":
                    soilEntity = new SwampSoil(
                            name,
                            soilData.getMass(),
                            soilData.getNitrogen(),
                            soilData.getWaterRetention(),
                            soilData.getSoilpH(),
                            soilData.getOrganicMatter(),
                            soilData.getWaterLogging()
                    );
                    break;
                case "TundraSoil":
                    soilEntity = new TundraSoil(
                            name,
                            soilData.getMass(),
                            soilData.getNitrogen(),
                            soilData.getWaterRetention(),
                            soilData.getSoilpH(),
                            soilData.getOrganicMatter(),
                            soilData.getPermafrostDepth()
                    );
                    break;
                default:
                    System.out.println("Invalid soil input");
                    break;
            }
            for (PairInput pair : soilData.getSections()) {
                simulations.getTerritory().getCell(pair.getY(), pair.getX()).setSoil(soilEntity);
            }
        }

        for (AirInput airData : currentSimulation.getTerritorySectionParams().getAir()) {
            String name = airData.getName();
            String type = airData.getType();

            Air airEntity = null;

            switch (type) {
                case "DesertAir":
                    airEntity = new DesertAir(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getDustParticles()
                    );
                    break;
                case "MountainAir":
                    airEntity = new MountainAir(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getAltitude()
                    );
                    break;
                case "PolarAir":
                    airEntity = new PolarAir(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getIceCrystalConcentration()
                    );
                    break;
                case "TemperateAir":
                    airEntity = new TemperateAir(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getPollenLevel()
                    );
                    break;
                case "TropicalAir":
                    airEntity = new TropicalAir(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getCo2Level()
                    );
                    break;
                default:
                    System.out.println("Invalid air input");
                    break;
            }

            for (PairInput pair : airData.getSections()) {
                simulations.getTerritory().getCell(pair.getY(), pair.getX()).setAir(airEntity);
            }
        }

        for (PlantInput plantData : currentSimulation.getTerritorySectionParams().getPlants()) {
            String name = plantData.getName();
            String type = plantData.getType();

            Plant plantEntity = null;
            switch (type) {
                case "Algae":
                    plantEntity = new Algae(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "Ferns":
                    plantEntity = new Ferns(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "FloweringPlants":
                    plantEntity = new FloweringPlants(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "GymnospermsPlants":
                    plantEntity = new GymnospermsPlants(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "Mosses":
                    plantEntity = new Mosses(
                            name,
                            plantData.getMass()
                    );
                    break;
                default:
                    System.out.println("Invalid plant input");
                    break;
            }
            for (PairInput pair : plantData.getSections()) {
                simulations.getTerritory().getCell(pair.getY(), pair.getX()).setPlant(plantEntity);
            }
        }

        for (WaterInput waterData : currentSimulation.getTerritorySectionParams().getWater()) {
            String name = waterData.getName();

            Water waterEntity = new Water(
                    name,
                    waterData.getMass(),
                    waterData.getSalinity(),
                    waterData.getPH(),
                    waterData.getPurity(),
                    waterData.getTurbidity(),
                    waterData.getContaminantIndex(),
                    waterData.isFrozen()
            );
            for (PairInput pair : waterData.getSections()) {
                simulations.getTerritory().getCell(pair.getY(), pair.getX()).setWater(waterEntity);
            }
        }

        for (AnimalInput animalData : currentSimulation.getTerritorySectionParams().getAnimals()) {
            String name = animalData.getName();
            String type = animalData.getType();

            Animal animalEntity = null;
            switch (type) {
                case "Herbivores":
                    animalEntity = new Herbivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Carnivores":
                    animalEntity = new Carnivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Detritivores":
                    animalEntity = new Detritivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Omnivores":
                    animalEntity = new Omnivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Parasites":
                    animalEntity = new Parasite(
                            name,
                            animalData.getMass()
                    );
                    break;
                default:
                    System.out.println("Invalid animal input");
                    break;
            }
            for (PairInput pair : animalData.getSections()) {
                simulations.getTerritory().getCell(pair.getY(), pair.getX()).setAnimal(animalEntity);
            }
        }
    }


    /**
     * @param inputPath  input file path
     * @param outputPath output file path
     * @throws IOException when files cannot be loaded.
     */
    public static void action(final String inputPath,
                              final String outputPath) throws IOException {

        InputLoader inputLoader = new InputLoader(inputPath);
        ArrayNode output = MAPPER.createArrayNode();
        Simulations simulations = new Simulations();

        for (CommandInput command : inputLoader.getCommands()) {
            ObjectNode resultNode = MAPPER.createObjectNode();
            resultNode.put("command", command.getCommand());

            if (simulations.isSimulationStarted() && simulations.getTeraBot() != null &&
                    simulations.getTeraBot().isCharging()) {
                    if (command.getTimestamp() < simulations.getTeraBot().getCharge_unit()) {
                        resultNode.put("message", "ERROR: Robot still charging. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                        output.add(resultNode);
                        continue; // Skip to the next command
                    } else {
                        simulations.getTeraBot().setCharging(false);
                    }
            }

            switch (command.getCommand()) {
                case "startSimulation":
                    if (simulations.isSimulationStarted()) {
                        resultNode.put("message",
                                "ERROR: Simulation already started. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                    } else {
                        SimulationInput currentSimulation = inputLoader.getSimulations().get(0);
                        simulations.startSimulation(currentSimulation);
                        initializeSimulation(simulations, currentSimulation);
                        resultNode.put("message", "Simulation has started.");
                        resultNode.put("timestamp", command.getTimestamp());
                    }
                    output.add(resultNode);
                    break;
                case "endSimulation":
                    if (!simulations.isSimulationStarted()) {
                        resultNode.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                    } else {
                        simulations.endSimulation();
                        resultNode.put("message", "Simulation has ended.");
                        resultNode.put("timestamp", command.getTimestamp());
                    }
                    output.add(resultNode);
                    break;
                case "printEnvConditions":
                    if (!simulations.isSimulationStarted()) {
                        resultNode.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                    } else {
                        resultNode.set("output", simulations.printEnvConditions());
                        resultNode.put("timestamp", command.getTimestamp());
                    }
                    output.add(resultNode);
                    break;
                case "printMap":
                    if (!simulations.isSimulationStarted()) {
                        resultNode.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                    } else {
                        resultNode.set("output", simulations.printMap());
                        resultNode.put("timestamp", command.getTimestamp());
                    }
                    output.add(resultNode);
                    break;
                case "moveRobot":
                    if (!simulations.isSimulationStarted()) {
                        resultNode.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                    } else {
                        resultNode.put("message", simulations.moveRobot());
                        resultNode.put("timestamp", command.getTimestamp());
                    }
                    output.add(resultNode);
                    break;
                case "getEnergyStatus":
                    if (!simulations.isSimulationStarted()) {
                        resultNode.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                    } else {
                        resultNode.put("message", simulations.getEnergyStatus());
                        resultNode.put("timestamp", command.getTimestamp());
                    }
                    output.add(resultNode);
                    break;
                case "rechargeBattery":
                    if (!simulations.isSimulationStarted()) {
                        resultNode.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                        resultNode.put("timestamp", command.getTimestamp());
                    } else {
                        resultNode.put("message", simulations.rechargeBattery(command.getTimeToCharge(),
                                                                                        command.getTimestamp()));
                        resultNode.put("timestamp", command.getTimestamp());
                    }
                    output.add(resultNode);
                    break;
                default:
                    resultNode.put("message", "ERROR: Invalid command");
                    resultNode.put("timestamp", command.getTimestamp());
                    output.add(resultNode);
                    break;
            }
        }

        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        WRITER.writeValue(outputFile, output);
    }
}
