package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.entity.plants.Grass;

import java.util.ArrayList;
import java.util.List;

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
                    List<Organism> plants = location.getSpecies().computeIfAbsent("Grass", k -> new ArrayList<>());

                    int maxPlantsPerCell = 2;
                    if (plants.size() < maxPlantsPerCell) {
                        try {
                            Grass newGrass = new Grass();
                            newGrass.setWeight(newGrass.getMaxWeight() * 0.1);
                            location.addOrganism(newGrass);
                            newGrass.setLocation(location);
                        } catch (Exception e) {
                            System.err.println("Ошибка при создании травы: " + e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
