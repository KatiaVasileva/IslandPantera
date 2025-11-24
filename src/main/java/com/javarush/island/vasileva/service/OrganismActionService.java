package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;

import java.util.concurrent.ExecutorService;
import java.util.function.Consumer;

public class OrganismActionService implements SimulationService {
    private final Island island;
    private final ExecutorService workerPool;
    private final Consumer<Organism> action;

    public OrganismActionService(Island island, ExecutorService workerPool, Consumer<Organism> action) {
        this.island = island;
        this.workerPool = workerPool;
        this.action = action;
    }

    @Override
    public void run() {
        if (island == null) return;
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (Organism organism : location.getOrganisms()) {
                    workerPool.submit(() -> {
                        if (organism.isALive()) {
                            action.accept(organism);
                        }
                    });
                }
            }
        }
    }
}
