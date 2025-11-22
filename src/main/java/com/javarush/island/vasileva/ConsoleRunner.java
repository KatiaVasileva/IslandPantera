package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.service.*;
import com.javarush.island.vasileva.view.ConsoleRenderer;

import java.lang.reflect.InvocationTargetException;

import static com.javarush.island.vasileva.config.Setting.*;

public class ConsoleRunner {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, InterruptedException {
        Island island = new Island(WIDTH, HEIGHT);
        init(island);

        ConsoleRenderer consoleRenderer = new ConsoleRenderer(island);
        consoleRenderer.setUseSymbols(true);

        System.out.println("Начальная конфигурация острова:");
        consoleRenderer.render();

        SimulationEngine engine = new SimulationEngine();
        engine.setIsland(island);
        engine.addService(new EatingService(island, engine.getWorkerPool()));
        engine.addService(new MovementService(island, engine.getWorkerPool()));
        engine.addService(new ReproductionService(island, engine.getWorkerPool()));
        engine.addService(new PlantGrowthService(island));
        engine.addService(new StatisticsService(island, consoleRenderer));

        engine.startSimulation(TICK_DURATION);

        System.out.println("\nЗапуск симуляции...");

        Thread.sleep(900_000);
        engine.shutdown();
    }
}
