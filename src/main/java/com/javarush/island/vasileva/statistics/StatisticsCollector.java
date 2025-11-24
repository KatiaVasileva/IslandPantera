package com.javarush.island.vasileva.statistics;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.entity.Organism;

import java.util.HashMap;
import java.util.Map;

public class StatisticsCollector {
    public Map<Class<? extends Organism>, Integer> collectOrganismCounts(Island island) {
        Map<Class<? extends Organism>, Integer> counts = new HashMap<>();

        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (Organism organism : location.getOrganisms()) {
                    if (organism.isALive()) {
                        Class<? extends Organism> organisms = organism.getClass();
                        counts.merge(organisms, 1, Integer::sum);
                    }
                }
            }
        }
        return counts;
    }
}
