package com.javarush.island.vasileva.util;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Location;

import static com.javarush.island.vasileva.config.Setting.getData;

public class Debug {
    public static void logEat(Organism organism, Organism item, Location loc) {
        System.out.printf("%s съел %s в [%d,%d]%n",
                getData(organism).name() + organism.getId(), getData(item).name() + item.getId(), loc.getX(), loc.getY());
    }

    public static void logReproduce(Organism organism, Organism offspring, Location loc) {
        System.out.printf("%s родил потомка %s в [%d,%d]%n",
                getData(organism).name() + organism.getId(), getData(offspring).name() + offspring.getId(), loc.getX(), loc.getY());
    }

    public static void logMove(Organism organism, Location loc) {
        System.out.printf("%s переместился в [%d,%d]%n",
                organism.getName() + organism.getId(), loc.getX(), loc.getY());
    }

}
