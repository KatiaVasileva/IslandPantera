package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.entity.map.Island;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.javarush.island.vasileva.config.Setting.*;
import static com.javarush.island.vasileva.config.Setting.THREAD_NUMBER;

@Getter
@Setter
public class SimulationEngine {
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    private final ExecutorService workerPool = Executors.newFixedThreadPool(THREAD_NUMBER);

    private final List<SimulationService> services = new ArrayList<>();

    private Island island;

    public void addService(SimulationService service) {
        services.add(service);
    }

    public void startSimulation(long tickDuration) {
        if (island == null) {
            throw new IllegalStateException("Island is not initialized");
        }
        for (SimulationService service : services) {
            scheduledExecutorService.scheduleWithFixedDelay(service, 0, tickDuration, TimeUnit.MILLISECONDS);
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
