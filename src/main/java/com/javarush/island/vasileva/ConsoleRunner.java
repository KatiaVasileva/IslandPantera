package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.service.SimulationEngine;
import com.javarush.island.vasileva.view.ConsoleRenderer;

import java.lang.reflect.InvocationTargetException;

import static com.javarush.island.vasileva.config.Setting.*;

public class ConsoleRunner {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Island island = new Island(WIDTH, HEIGHT);
        init(island);

        ConsoleRenderer consoleRenderer = new ConsoleRenderer(island);
        consoleRenderer.setUseSymbols(true);

        System.out.println("Начальная конфигурация острова:");
        consoleRenderer.render();

        SimulationEngine engine = new SimulationEngine();
        engine.setIsland(island);
        engine.initRenderer(consoleRenderer);
        engine.startSimulation(TICK_DURATION);

        System.out.println("\nЗапуск симуляции...");

//        Thread.sleep(300000);
//        engine.shutdown();
    }
}
