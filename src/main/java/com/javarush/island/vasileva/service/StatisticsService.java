package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.plants.Plant;
import com.javarush.island.vasileva.statiistics.StatisticsCollector;
import com.javarush.island.vasileva.statiistics.StatisticsFormatter;

import java.util.Map;

public class StatisticsService {
    final StatisticsCollector collector = new StatisticsCollector();
    private final StatisticsFormatter formatter = new StatisticsFormatter();

    public String generateReport(Island island, int tickCounter) {
        Map<Class<? extends Organism>, Integer> animalCounts = collector.collectAnimalCounts(island);
        Map<Class<? extends Plant>, Integer> plantCounts = collector.collectPlantCounts(island);

        return formatter.format(
                animalCounts,
                plantCounts,
                tickCounter
        );
    }

    public void printReport(Island island, int tickCounter) {
        System.out.println(generateReport(island, tickCounter));
    }
}
