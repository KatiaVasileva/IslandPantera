package com.javarush.island.vasileva.config;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.javarush.island.vasileva.config.Setting.HERBIVORES_TYPES;
import static com.javarush.island.vasileva.config.Setting.HERBIVORES_TYPES_THAT_EAT_WORMS;

public class EatingChances {
    private static final Map<Class<?>, Map<Class<?>, Double>> EATING_CHANCES = new HashMap<>();

    private EatingChances() {
    }

    static {
        setDiet(Wolf.class, new DietBuilder()
                .add(Horse.class, 0.1)
                .add(Deer.class, 0.15)
                .add(Rabbit.class, 0.6)
                .add(Mouse.class, 0.8)
                .add(Goat.class, 0.6)
                .add(Sheep.class, 0.7)
                .add(Boar.class, 0.15)
                .add(Bull.class, 0.1)
                .add(Duck.class, 0.4)
        );

        setDiet(Boa.class, new DietBuilder()
                .add(Fox.class, 0.15)
                .add(Rabbit.class, 0.2)
                .add(Mouse.class, 0.4)
                .add(Duck.class, 0.1)
        );

        setDiet(Fox.class, new DietBuilder()
                .add(Rabbit.class, 0.7)
                .add(Mouse.class, 0.9)
                .add(Duck.class, 0.6)
                .add(Worm.class, 0.4)
        );

        setDiet(Bear.class, new DietBuilder()
                .add(Boa.class, 0.8)
                .add(Horse.class, 0.4)
                .add(Deer.class, 0.8)
                .add(Rabbit.class, 0.8)
                .add(Mouse.class, 0.9)
                .add(Goat.class, 0.7)
                .add(Sheep.class, 0.7)
                .add(Bull.class, 0.2)
                .add(Duck.class, 0.1)
        );

        setDiet(Eagle.class, new DietBuilder()
                .add(Fox.class, 0.1)
                .add(Rabbit.class, 0.9)
                .add(Mouse.class, 0.9)
                .add(Duck.class, 0.8)
        );

        setDiet(Boar.class, new DietBuilder()
                .add(Mouse.class, 0.5)
                .add(Worm.class, 0.9)
                .add(Grass.class, 1.0)
        );

        for (Class<?> herbivore : List.of(HERBIVORES_TYPES)) {
            DietBuilder dietBuilder = new DietBuilder().add(Grass.class, 1.0);
            if (List.of(HERBIVORES_TYPES_THAT_EAT_WORMS).contains(herbivore))
                dietBuilder.add(Worm.class, 0.9);
            setDiet(herbivore, dietBuilder);
        }
    }

    public static double getChances(Class<? extends Organism> animal, Class<? extends Organism> prey) {
        Map<Class<?>, Double> animalDiet = EATING_CHANCES.get(animal);
        if (animalDiet == null || animalDiet.get(prey) == null) {
            return 0;
        }
        return animalDiet.get(prey);
    }

    public static void setDiet(Class<?> animal, DietBuilder dietBuilder) {
        EATING_CHANCES.put(animal, dietBuilder.build());
    }

    public static class DietBuilder {
        private final Map<Class<?>, Double> diet = new HashMap<>();

        public DietBuilder add(Class<?> prey, double chances) {
            diet.put(prey, chances);
            return this;
        }

        public Map<Class<?>, Double> build() {
            return Collections.unmodifiableMap(diet);
        }
    }
}
