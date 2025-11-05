package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.Species;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class Main {
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

        Species wolf = new Wolf();
        wolf.die();
        if (!wolf.isALive())  {
            System.out.println("Wolf is dead");
        }

        Animal bear = new Bear();
        bear.eat();
        bear.move();
        bear.reproduce();

        Animal rabbit = new Rabbit();
        rabbit.eat();
        rabbit.move();
        rabbit.reproduce();

    }
}
