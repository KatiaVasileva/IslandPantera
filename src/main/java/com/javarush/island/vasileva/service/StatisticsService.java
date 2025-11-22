package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.statiistics.StatisticsGenerator;
import com.javarush.island.vasileva.view.ConsoleRenderer;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsService implements SimulationService{
    private final ConsoleRenderer consoleRenderer;
    private final StatisticsGenerator statisticsGenerator;
    private final AtomicInteger tickCounter = new AtomicInteger(0);
    @Setter
    private Island island;

    public StatisticsService(Island island, ConsoleRenderer consoleRenderer) {
        this.island = island;
        this.consoleRenderer = consoleRenderer;
        this.statisticsGenerator = new StatisticsGenerator();
    }

    @Override
    public void run() {
        int currentTick = tickCounter.incrementAndGet();
        statisticsGenerator.printReport(island, currentTick);

        if (consoleRenderer != null) {
            consoleRenderer.render();
        }
    }
}
