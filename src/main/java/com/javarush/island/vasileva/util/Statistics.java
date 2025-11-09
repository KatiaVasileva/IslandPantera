package com.javarush.island.vasileva.util;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;
import com.javarush.island.vasileva.entity.plants.Plant;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

public class Statistics {
    private Statistics() {}

    public static void printStatistics(int totalAnimals, Island island) {
        System.out.println("Total animals: " + totalAnimals +
                "\n" + getAnimalImage(Wolf.class) + " - " + countAnimalsBySpecies(Wolf.class, island) +
                " | " + getAnimalImage(Boa.class) + " - " + countAnimalsBySpecies(Boa.class, island) +
                " | " + getAnimalImage(Bear.class) + " - " + countAnimalsBySpecies(Bear.class, island) +
                " | " + getAnimalImage(Eagle.class) + " - " + countAnimalsBySpecies(Eagle.class,  island) +
                " | " + getAnimalImage(Boar.class) + " - " + countAnimalsBySpecies(Boar.class, island) +
                " | " + getAnimalImage(Fox.class) + " - " + countAnimalsBySpecies(Fox.class, island) +
                " | " + getAnimalImage(Horse.class) + " - " + countAnimalsBySpecies(Horse.class, island) +
                " | " + getAnimalImage(Rabbit.class) + " - " + countAnimalsBySpecies(Rabbit.class, island) +
                " | " + getAnimalImage(Bull.class) + " - " + countAnimalsBySpecies(Bull.class, island) +
                " | " + getAnimalImage(Deer.class) + " - " + countAnimalsBySpecies(Deer.class, island) +
                " | " + getAnimalImage(Goat.class) + " - " + countAnimalsBySpecies(Goat.class, island) +
                " | " + getAnimalImage(Mouse.class) + " - " + countAnimalsBySpecies(Mouse.class, island) +
                " | " + getAnimalImage(Sheep.class) + " - " + countAnimalsBySpecies(Sheep.class, island) +
                " | " + getAnimalImage(Duck.class) + " - " + countAnimalsBySpecies(Duck.class, island) +
                " | " + getAnimalImage(Worm.class) + " - " + countAnimalsBySpecies(Worm.class, island) +
                " | " + getAnimalImage(Grass.class) + " - " + countPlantsBySpecies(Grass.class, island));
    }

    public static int countAnimalsBySpecies(AnnotatedElement annotatedElement, Island island) {
        List<? super Animal> animalsBySpecies = new ArrayList<>();
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                List<Animal> animals = location.getAnimals();
                for (Animal animal : animals) {
                    Annotation[] annotations = annotatedElement.getAnnotations();
                    if (animal.isALive() && animal.getClass().getSimpleName().equals(((SpeciesCharacteristics) annotations[0]).name())) {
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
                    if (plant.isALive() && plant.getClass().getSimpleName().equals(((SpeciesCharacteristics) annotations[0]).name())) {
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
            if (annotation instanceof SpeciesCharacteristics speciesCharacteristics) {
                image = speciesCharacteristics.image();
            }
        }
        return image;
    }
}
