package com.javarush.island.vasileva.util;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;
import com.javarush.island.vasileva.entity.plants.Plant;
import com.javarush.island.vasileva.view.SymbolMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

import static com.javarush.island.vasileva.view.SymbolMap.getAbbrev;

public class Statistics {
    private Statistics() {}

    public static void printStatistics(int totalAnimals, int totalPlants, int tickCounter, Island island) {
        System.out.println("\n--- ТАКТ " + tickCounter + "---\n" + "Total animals: " + totalAnimals + " - Total plants: " + totalPlants +
                "\n" + getAnimalImage2(Wolf.class) + " (" + getAbbrev(Wolf.class) + ") - " + countAnimalsBySpecies(Wolf.class, island) +
                " | " + getAnimalImage(Boa.class) + " (" + getAbbrev(Boa.class) + ") - " + countAnimalsBySpecies(Boa.class, island) +
                " | " + getAnimalImage(Bear.class) + " (" + getAbbrev(Bear.class) + ") - " + countAnimalsBySpecies(Bear.class, island) +
                " | " + getAnimalImage(Eagle.class) + " (" + getAbbrev(Eagle.class) + ") - " + countAnimalsBySpecies(Eagle.class,  island) +
                " | " + getAnimalImage(Boar.class) + " (" + getAbbrev(Boar.class) + ") - " + countAnimalsBySpecies(Boar.class, island) +
                " | " + getAnimalImage(Fox.class) + " (" + getAbbrev(Fox.class) + ") - " + countAnimalsBySpecies(Fox.class, island) +
                " | " + getAnimalImage(Horse.class) + " (" + getAbbrev(Horse.class) + ") - " + countAnimalsBySpecies(Horse.class, island) +
                " | " + getAnimalImage(Rabbit.class) + " (" + getAbbrev(Rabbit.class) + ") - " + countAnimalsBySpecies(Rabbit.class, island) +
                " \n" + getAnimalImage(Bull.class) + " (" + getAbbrev(Bull.class) + ") - " + countAnimalsBySpecies(Bull.class, island) +
                " | " + getAnimalImage(Deer.class) + " (" + getAbbrev(Deer.class) + ") - " + countAnimalsBySpecies(Deer.class, island) +
                " | " + getAnimalImage(Goat.class) + " (" + getAbbrev(Goat.class) + ") - " + countAnimalsBySpecies(Goat.class, island) +
                " | " + getAnimalImage(Mouse.class) + " (" + getAbbrev(Mouse.class) + ") - " + countAnimalsBySpecies(Mouse.class, island) +
                " | " + getAnimalImage(Sheep.class) + " (" + getAbbrev(Sheep.class) + ") - " + countAnimalsBySpecies(Sheep.class, island) +
                " | " + getAnimalImage(Duck.class) + " (" + getAbbrev(Duck.class) + ") - " + countAnimalsBySpecies(Duck.class, island) +
                " | " + getAnimalImage(Worm.class) + " (" + getAbbrev(Worm.class) + ") - " + countAnimalsBySpecies(Worm.class, island) +
                " | " + getAnimalImage(Grass.class) + " (" + getAbbrev(Grass.class) + ") - " + countPlantsBySpecies(Grass.class, island));
    }

    public static int countAnimalsBySpecies(AnnotatedElement annotatedElement, Island island) {
        List<? super Animal> animalsBySpecies = new ArrayList<>();
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                List<Animal> animals = location.getAnimals();
                for (Animal animal : animals) {
                    Annotation[] annotations = annotatedElement.getAnnotations();
                    if (animal.isALive() && animal.getClass().getSimpleName().equals(((OrganismData) annotations[0]).name())) {
                        animalsBySpecies.add(animal);
                    }
                }
            }
        }
        return animalsBySpecies.size();
    }

    public static int countPlantsBySpecies(AnnotatedElement annotatedElement, Island island) {
        List<? super Plant> plantsBySpecies = new ArrayList<>();
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                List<Plant> plants = location.getPlants();
                for (Plant plant : plants) {
                    Annotation[] annotations = annotatedElement.getAnnotations();
                    if (plant.isALive() && plant.getClass().getSimpleName().equals(((OrganismData) annotations[0]).name())) {
                        plantsBySpecies.add(plant);
                    }
                }
            }
        }
        return plantsBySpecies.size();
    }

    public static String getAnimalImage(Class<? extends Organism> speciesClass) {
        String image = "";
        Annotation[] annotations = speciesClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof OrganismData organismData) {
                image = organismData.image();
            }
        }
        return image;
    }

    public static String getAnimalImage2(Class<? extends Organism> organismClass) {
        return SymbolMap.getSymbol(organismClass);

    }
}
