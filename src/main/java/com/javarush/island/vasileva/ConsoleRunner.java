package com.javarush.island.vasileva;

import com.javarush.island.vasileva.config.IslandConfig;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.service.*;
import com.javarush.island.vasileva.view.ConsoleRenderer;

import java.lang.reflect.InvocationTargetException;

import static com.javarush.island.vasileva.config.Setting.*;

public class ConsoleRunner {
    public static void main(String[] args) {
        try {
            Island island = new Island(WIDTH, HEIGHT);
            IslandConfig islandConfig = new IslandConfig(island);

            islandConfig.initIsland(island);

            ConsoleRenderer consoleRenderer = new ConsoleRenderer(island);
            consoleRenderer.setUseSymbols(true);

            SimulationEngine engine = new SimulationEngine();
            engine.setIsland(island);

            islandConfig.configureServices(engine, consoleRenderer);

            System.out.println("\nЗапуск симуляции...");
            engine.startSimulation(TICK_DURATION);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
