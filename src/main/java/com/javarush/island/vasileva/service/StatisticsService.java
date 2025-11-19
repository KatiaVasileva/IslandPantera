package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.statiistics.StatisticsCollector;
import com.javarush.island.vasileva.statiistics.StatisticsFormatter;

import java.util.Map;

public class StatisticsService {
    final StatisticsCollector collector = new StatisticsCollector();
    private final StatisticsFormatter formatter = new StatisticsFormatter();

    public String generateReport(Island island, int tickCounter) {
        Map<Class<? extends Organism>, Integer> organismCounts = collector.collectOrganismCounts(island);

        return formatter.format(
                organismCounts,
                tickCounter
        );
    }

    public void printReport(Island island, int tickCounter) {
        System.out.println(generateReport(island, tickCounter));
    }
}
