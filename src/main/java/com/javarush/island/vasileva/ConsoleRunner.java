package com.javarush.island.vasileva;

import com.javarush.island.vasileva.config.Setting;

import java.lang.reflect.InvocationTargetException;

public class ConsoleRunner {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Island island = new Island(Setting.WIDTH, Setting.HEIGHT);

        Setting.init(island);

        System.out.println("start simulation");
        island.startSimulation(Setting.TICK_DURATION);

    }
}
