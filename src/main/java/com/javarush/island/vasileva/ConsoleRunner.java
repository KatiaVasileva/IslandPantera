package com.javarush.island.vasileva;

import com.javarush.island.vasileva.config.Setting;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.Wolf;

import java.lang.reflect.InvocationTargetException;

import static com.javarush.island.vasileva.config.EatingChances.getChances;

public class ConsoleRunner {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Island island = new Island(Setting.WIDTH, Setting.HEIGHT);

        Setting.init(island);

        System.out.println(getChances(Wolf.class, Mouse.class));

        System.out.println("start simulation");
        island.startSimulation(Setting.TICK_DURATION);

    }
}
