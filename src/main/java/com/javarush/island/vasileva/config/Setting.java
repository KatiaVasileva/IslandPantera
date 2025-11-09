package com.javarush.island.vasileva.config;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;

public class Setting {
    public static final int CORE_POOL_SIZE = 3;
    public static final int THREAD_NUMBER = 20;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    public static final int TICK_DURATION = 1000;

    private Setting() {}

    public static void init(Island island) {
        new Wolf().placeOrganisms(island);
        new Bear().placeOrganisms(island);
        new Boar().placeOrganisms(island);
        new Fox().placeOrganisms(island);
        new Eagle().placeOrganisms(island);
        new Horse().placeOrganisms(island);
        new Sheep().placeOrganisms(island);
        new Goat().placeOrganisms(island);
        new Mouse().placeOrganisms(island);
        new Duck().placeOrganisms(island);
        new Worm().placeOrganisms(island);
        new Boa().placeOrganisms(island);
        new Bull().placeOrganisms(island);
        new Deer().placeOrganisms(island);
        new Rabbit().placeOrganisms(island);
        new Grass().placeOrganisms(island);
    }

}
