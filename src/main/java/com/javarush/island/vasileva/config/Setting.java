package com.javarush.island.vasileva.config;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;

import java.lang.reflect.InvocationTargetException;

public class Setting {
    public static final int CORE_POOL_SIZE = 3;
    public static final int THREAD_NUMBER = 20;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    public static final int TICK_DURATION = 1000;

    public static final Class<?>[] TYPES = {
            Wolf.class, Bear.class, Horse.class, Duck.class, Rabbit.class, Mouse.class, Deer.class,
            Boar.class, Eagle.class, Fox.class, Goat.class, Sheep.class, Bull.class, Boa.class,
            Worm.class, Grass.class
    };

    public static final Class<?>[] HERBIVORES_TYPES = {
            Horse.class, Duck.class, Rabbit.class, Mouse.class, Deer.class,
            Boar.class, Eagle.class, Fox.class, Goat.class, Sheep.class, Bull.class, Boa.class
    };

    public static final Class<?>[] HERBIVORES_TYPES_THAT_EAT_WORMS = {
            Duck.class, Mouse.class
    };

    private Setting() {
    }

    public static void init(Island island) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<?> type : TYPES) {
            Organism org = (Organism) type.getConstructor().newInstance();
            org.placeOrganisms(island);
        }
    }
}
