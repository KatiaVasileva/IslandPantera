package com.javarush.island.vasileva.statiistics;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.plants.Plant;

import java.util.HashMap;
import java.util.Map;

public class StatisticsCollector {

    public Map<Class<? extends Organism>, Integer> collectAnimalCounts(Island island) {
        Map<Class<? extends Organism>, Integer> counts = new HashMap<>();

        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (Animal animal : location.getAnimals()) {
                    if (animal.isALive()) {
                        Class<? extends Organism> organisms = animal.getClass();
                        counts.merge(organisms, 1, Integer::sum);
                    }
                }
            }
        }
        return counts;
    }

    public Map<Class<? extends Plant>, Integer> collectPlantCounts(Island island) {
        Map<Class<? extends Plant>, Integer> counts = new HashMap<>();

        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (Plant plant : location.getPlants()) {
                    if (plant.isALive()) {
                        Class<? extends Plant> organisms = plant.getClass();
                        counts.merge(organisms, 1, Integer::sum);
                    }
                }
            }
        }
        return counts;
    }
}
