package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.Species;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static final int WIDTH = 100;
    public static final int HEIGHT = 20;
    public static final int TICK_DURATION = 1000;
    public static final int START_WOLVES = 5;
    public static final int START_HORSES = 10;

    public static void main(String[] args) {

        List<Species> species = Arrays.asList(new Wolf(), new Eagle(), new Horse(), new Grass(),
                new Bull(), new Deer(), new Duck(), new Goat(), new Mouse(), new Rabbit(), new Sheep(),
                new Worm(), new Bear(), new Boa(), new Boar(), new Fox());

        species.forEach(e -> System.out.println(e.getName() + " " + e.getWeight()));

        System.out.println();

        for (Species element : species) {
            Annotation[] annotations = element.getClass().getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof SpeciesCharacteristics speciesCharacteristics) {
                    System.out.println(speciesCharacteristics.name() + " " + speciesCharacteristics.weight());
                }
            }
        }

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
