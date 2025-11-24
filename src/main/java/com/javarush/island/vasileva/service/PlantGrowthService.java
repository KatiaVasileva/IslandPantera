package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.entity.plants.Plant;
import com.javarush.island.vasileva.repository.OrganismFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import static com.javarush.island.vasileva.config.Setting.*;

public class PlantGrowthService implements SimulationService {
    private final Island island;

    public PlantGrowthService(Island island) {
        this.island = island;
    }

    @Override
    public void run() {
        if (island == null) return;

        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                synchronized (location) {
                    for (Class<? extends Plant> plantClass : PLANTS_TYPES) {
                        List<Organism> existingPlants = location.getSpecies()
                                .getOrDefault(plantClass, Collections.emptyList());

                        if (existingPlants.size() >= MAX_PLANTS_PER_CELL) {
                            continue;
                        }

                        try {
                            OrganismFactory.createOrganism(plantClass, location,
                                    plantClass.getAnnotation(OrganismData.class).maxWeight(),
                                    MIN_WEIGHT_FACTOR
                            );
                        } catch (InstantiationException | IllegalAccessException |
                                 InvocationTargetException | NoSuchMethodException e) {
                            System.err.println("Ошибка создания растения " +
                                    plantClass.getSimpleName() + ": " + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
