package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;

import java.util.concurrent.ExecutorService;

public class MovementService implements SimulationService {
    private final Island island;
    private final ExecutorService workerPool;

    public MovementService(Island island, ExecutorService workerPool) {
        this.island = island;
        this.workerPool = workerPool;
    }

    @Override
    public void run() {
        if (island == null) return;
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (Organism organism : location.getOrganisms()) {
                    workerPool.submit(() -> {
                        if (organism.isALive()) {
                            organism.move(island);
                        }
                    });
                }
            }
        }
    }
}
