package com.javarush.island.vasileva.config;

import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;
import com.javarush.island.vasileva.entity.plants.Mushroom;
import com.javarush.island.vasileva.entity.plants.Plant;

import java.util.List;

public class Setting {
    public static final int CORE_POOL_SIZE = 3;
    public static final int THREAD_NUMBER = 20;
    public static final int TICK_DURATION = 1000;

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int SHOW_WIDTH = 10;
    public static final int SHOW_HEIGHT = 10;
    public static final int CELL_WIDTH = 7;
    public static final int NUMBER_OF_SYMBOLS_IN_CELL = 3;
    public static final int ORGANISM_PLACEMENT_CYCLES = 2;
    public static final int MAX_PLANTS_PER_CELL = 3;
    public static final double MIN_WEIGHT_FACTOR = 0.8;
    public static final int MIN_SPECIMENS_REQUIRED = 2;
    public static final double LOSE_WEIGHT_FACTOR = 0.99;
    public static final double DIE_WEIGHT_FACTOR = 0.1;
    public static final int MIN_AGE_FOR_REPRODUCTION = 5;
    public static final int REPRODUCTION_FREQUENCY = 5;
    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static final List<Class<?>> TYPES = List.of(
            Wolf.class,
            Bear.class,
            Horse.class,
            Duck.class,
            Rabbit.class,
            Mouse.class,
            Deer.class,
            Boar.class,
            Eagle.class,
            Fox.class,
            Goat.class,
            Sheep.class,
            Bull.class,
            Boa.class,
            Worm.class,
            Grass.class,
            Mushroom.class
    );

    public static final List<Class<?>> HERBIVORES_TYPES = List.of(
            Horse.class, Duck.class, Rabbit.class, Mouse.class, Deer.class,
            Boar.class, Goat.class, Sheep.class, Bull.class
    );

    public static final List<Class<?>> HERBIVORES_TYPES_THAT_EAT_WORMS = List.of(
            Duck.class, Mouse.class
    );

    public static final List<Class<? extends Plant>> PLANTS_TYPES = List.of(Grass.class, Mushroom.class);

    public static final List<Class<?>> HERBIVORES_TYPES_THAT_EAT_MUSHROOMS = List.of(
            Deer.class, Rabbit.class
    );

    public static final String WOLF_IMAGE = "\uD83D\uDC3A";
    public static final String BOA_IMAGE = "\uD83D\uDC0D";
    public static final String BEAR_IMAGE = "\uD83D\uDC3B";
    public static final String EAGLE_IMAGE = "\uD83E\uDD85";
    public static final String BOAR_IMAGE = "\uD83D\uDC17";
    public static final String FOX_IMAGE = "\uD83E\uDD8A";
    public static final String HORSE_IMAGE = "\uD83D\uDC0E";
    public static final String RABBIT_IMAGE = "\uD83D\uDC07";
    public static final String BULL_IMAGE = "\uD83D\uDC03";
    public static final String DEER_IMAGE = "\uD83E\uDD8C";
    public static final String GOAT_IMAGE = "\uD83D\uDC10";
    public static final String MOUSE_IMAGE = "\uD83D\uDC01";
    public static final String SHEEP_IMAGE = "\uD83D\uDC11";
    public static final String DUCK_IMAGE = "\uD83E\uDD86";
    public static final String WORM_IMAGE = "\uD83D\uDC1B";
    public static final String GRASS_IMAGE = "\uD83C\uDF3F";
    public static final String MUSHROOM_IMAGE = "\uD83C\uDF44";

    private Setting() {
    }
}
