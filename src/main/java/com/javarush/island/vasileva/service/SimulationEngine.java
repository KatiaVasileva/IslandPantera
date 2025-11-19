package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.view.ConsoleRenderer;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.javarush.island.vasileva.config.Setting.*;
import static com.javarush.island.vasileva.config.Setting.THREAD_NUMBER;

public class SimulationEngine {
    private ConsoleRenderer consoleRenderer;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    private final ExecutorService workerPool = Executors.newFixedThreadPool(THREAD_NUMBER);
    private final AtomicInteger tickCounter = new AtomicInteger(0);
    private final StatisticsService statisticsService = new StatisticsService();
    @Setter
    private Island island;

    public void initRenderer(ConsoleRenderer consoleRenderer) {
        this.consoleRenderer = consoleRenderer;
    }

    public void startSimulation(long tickDuration) {
        if (island == null) {
            throw new IllegalStateException("Island is not initialized");
        }
        scheduledExecutorService.scheduleWithFixedDelay(this::processOrganisms, 0, tickDuration, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(this::getStatistics, 0, tickDuration, TimeUnit.MILLISECONDS);
    }

    public void processOrganisms() {
        if (island == null) return;
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (Organism organism : location.getOrganisms()) {
                    workerPool.submit(() -> {
                        if (organism.isALive()) {
                            organism.eat();
//                            try {
//                                organism.reproduce();
//                            } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
//                                throw new RuntimeException(e);
//                            }
                            organism.move(island);
                        }
                    });
                }
            }
        }
    }

    private void getStatistics() {
            int currentTick = tickCounter.incrementAndGet();

            statisticsService.printReport(island, currentTick);

            if (consoleRenderer != null) {
                consoleRenderer.render();
            }
    }

    public void shutdown() {
        workerPool.shutdown();
        scheduledExecutorService.shutdown();

        try {
            if (!workerPool.awaitTermination(60, TimeUnit.SECONDS)) {
                workerPool.shutdownNow();
            }
            if (!scheduledExecutorService.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduledExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            workerPool.shutdownNow();
            scheduledExecutorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
