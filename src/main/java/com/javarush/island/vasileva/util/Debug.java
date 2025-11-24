package com.javarush.island.vasileva.util;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Location;

public class Debug {
    public Debug() {
    }

    public static void logEat(Organism organism, Organism item, Location loc) {
        System.out.printf("%s съел %s в [%d,%d]%n",
                organism.getName() + organism.getId(), item.getName() + item.getId(), loc.getX(), loc.getY());
    }

    public static void logReproduce(Organism organism, Organism offspring, Location loc) {
        System.out.printf("%s родил потомка %s в [%d,%d]%n",
                organism.getName() + organism.getId(), offspring.getName() + offspring.getId(), loc.getX(), loc.getY());
    }

    public static void logMove(Organism organism, Location loc, Location newLoc) {
        System.out.printf("%s переместился из [%d,%d] в [%d,%d]%n",
                organism.getName() + organism.getId(), loc.getX(), loc.getY(), newLoc.getX(), newLoc.getY());
    }

    public static void logWeightBeforeEating(Organism organism, Organism food) {
                System.out.printf("weight of %s before eating = %f, weight of prey: %s = %f\n",
                        organism.getName() +  organism.getId(), organism.getWeight(), food.getName() + food.getId(),food.getWeight());
    }

    public static void logWeightAfterEating(Organism organism, Organism food) {
        System.out.printf("weight of %s after eating = %f, weight of prey: %s = %f\n",
                organism.getName() +  organism.getId(), organism.getWeight(), food.getName() + food.getId(),food.getWeight());

    }
}
