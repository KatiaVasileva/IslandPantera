package com.javarush.island.vasileva;

import com.javarush.island.vasileva.config.Setting;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.view.ConsoleRenderer;

import java.lang.reflect.InvocationTargetException;

public class ConsoleRunner {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Island island = new Island(Setting.WIDTH, Setting.HEIGHT);

        Setting.init(island);

        ConsoleRenderer consoleRenderer = new ConsoleRenderer(island);
        consoleRenderer.setUseSymbols(true);

        System.out.println("Начальная конфигурация острова:");

        consoleRenderer.render();

        System.out.println("\nЗапуск симуляции...");

        island.initRenderer(consoleRenderer);

        island.startSimulation(Setting.TICK_DURATION);
    }
}
