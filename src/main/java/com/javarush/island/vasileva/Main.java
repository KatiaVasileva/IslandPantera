package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;

public class Main {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    public static final int TICK_DURATION = 1000;
    public static final int START_WOLVES = 5;
    public static final int START_HORSES = 10;

    public static void main(String[] args) {

        Island island = new Island(WIDTH, HEIGHT);

        for (int i = 0; i < START_WOLVES; i++) {
            Wolf wolf1 = new Wolf();
            island.getLocation(10, 10).addAnimal(wolf1);
        }

        for (int i = 0; i < START_HORSES; i++) {
            Horse horse = new Horse();
            island.getLocation(5, 5).addAnimal(horse);
        }

        System.out.println("start simulation");
        island.startSimulation(TICK_DURATION);

    }
}
