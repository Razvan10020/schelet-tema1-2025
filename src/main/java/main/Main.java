package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;

import entities.Air;
import entities.Animal;
import entities.Plant;
import entities.Soil;
import entities.Water;

import entities.air_types.Desert;
import entities.air_types.Montan;
import entities.air_types.Polar;
import entities.air_types.Temperat;
import entities.air_types.Tropical;

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
import fileio.TerritorySectionParamsInput;
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

    /**
     * @param inputPath input file path
     * @param outputPath output file path
     * @throws IOException when files cannot be loaded.
     */
    public static void action(final String inputPath,
                              final String outputPath) throws IOException {

        InputLoader inputLoader = new InputLoader(inputPath);
        ArrayNode output = MAPPER.createArrayNode();

        SimulationInput currentSimulation = inputLoader.getSimulations().get(0);

        for (SoilInput soilData : currentSimulation.getTerritorySectionParams().getSoil()) {
            String name =  soilData.getName();
            String type = soilData.getType();

            Soil soilEntity = null;
            switch (type) {
                case "ForestSoil" :
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
                case "DesertSoil" :
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
                case "GrasslandSoil" :
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
                case "SwampSoil" :
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
                case "TundraSoil" :
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
                default :
                    System.out.println("Invalid soil input");
                    break;
            }
        }

        for (AirInput airData : currentSimulation.getTerritorySectionParams().getAir()) {
            String name = airData.getName();
            String type = airData.getType();

            Air airEntity = null;

            switch (type) {
                case "DesertAir" :
                    airEntity = new Desert(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getDustParticles()
                    );
                    break;
                case "MountainAir" :
                    airEntity = new Montan(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getAltitude()
                    );
                    break;
                case "PolarAir" :
                    airEntity = new Polar(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getIceCrystalConcentration()
                    );
                    break;
                case "TemperateAir" :
                    airEntity = new Temperat(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getPollenLevel()
                    );
                    break;
                case "TropicalAir" :
                    airEntity = new Tropical(
                            name,
                            airData.getMass(),
                            airData.getHumidity(),
                            airData.getTemperature(),
                            airData.getOxygenLevel(),
                            airData.getCo2Level()
                    );
                    break;
                default :
                    System.out.println("Invalid air input");
                    break;
            }
        }

        for (PlantInput plantData : currentSimulation.getTerritorySectionParams().getPlants()) {
            String name = plantData.getName();
            String type = plantData.getType();

            Plant plantEntity = null;
            switch (type) {
                case "Algae" :
                    plantEntity = new Algae(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "Ferns" :
                    plantEntity = new Ferns(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "FloweringPlant" :
                    plantEntity = new FloweringPlants(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "GymnospermsPlants" :
                    plantEntity = new GymnospermsPlants(
                            name,
                            plantData.getMass()
                    );
                    break;
                case "Mosses" :
                    plantEntity = new Mosses(
                            name,
                            plantData.getMass()
                    );
                    break;
                default:
                    System.out.println("Invalid plant input");
                    break;
            }
        }

        for (WaterInput waterData : currentSimulation.getTerritorySectionParams().getWater()) {
            String name = waterData.getName();
            String type = waterData.getType();

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
        }

        for (AnimalInput animalData : currentSimulation.getTerritorySectionParams().getAnimals()) {
            String name = animalData.getName();
            String type = animalData.getType();

            Animal animalEntity = null;
            switch (type) {
                case "Herbivore" :
                    animalEntity = new Herbivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Carnivore" :
                    animalEntity = new Carnivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Detritivore" :
                    animalEntity = new Detritivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Omnivore" :
                    animalEntity = new Omnivore(
                            name,
                            animalData.getMass()
                    );
                    break;
                case "Parasite" :
                    animalEntity = new Parasite(
                            name,
                            animalData.getMass()
                    );
                    break;
                default :
                    System.out.println("Invalid animal input");
                    break;
            }
        }
        /*
         * TODO Implement your function here
         *
         * How to add output to the output array?
         * There are multiple ways to do this, here is one example:
         *
         *
         * ObjectNode objectNode = MAPPER.createObjectNode();
         * objectNode.put("field_name", "field_value");
         *
         * ArrayNode arrayNode = MAPPER.createArrayNode();
         * arrayNode.add(objectNode);
         *
         * output.add(arrayNode);
         * output.add(objectNode);
         *
         */

        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        WRITER.writeValue(outputFile, output);
    }
}
