package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.Species;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;
import lombok.Getter;
import lombok.Setter;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class Island {
    private final Location[][] grid;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public Island(int width, int height) {
        grid = new Location[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = new Location(i, j);
            }
        }
    }

    public Location getLocation(int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
            return grid[x][y];
        }
        return null;
    }

    public void startSimulation(long tickDuration) {
        scheduledExecutorService.scheduleAtFixedRate(this::growPlants, 0, tickDuration, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(this::processAnimals, 0, tickDuration, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleAtFixedRate(this::getStatistics, 0, tickDuration * 5, TimeUnit.MILLISECONDS);
    }

    public void growPlants() {
        for (Location[] row : grid) {
            for (Location location : row) {
                if (location.getPlants().size() < 200) {
                    location.addPlant(new Grass());
                }
            }
        }
    }

    public void processAnimals() {
        for (Location[] row : grid) {
            for (Location location : row) {
                for (Animal animal : location.getAnimals()) {
                    executorService.submit(() -> {
                        if (animal.isALive()) {
                            animal.eat();
                            animal.reproduce();
                            animal.move();
                        }
                    });
                }
            }
        }
    }

    private void getStatistics() {
        int totalAnimals = 0;
        for (Location[] row : grid) {
            for (Location location : row) {
                totalAnimals += location.getAnimals().size();
            }
        }
        printStatistics(totalAnimals);
    }

    public void printStatistics(int totalAnimals) {
        System.out.println("Total animals: " + totalAnimals +
                "\n" + getAnimalImage(Wolf.class) + " - " + countAnimalsBySpecies(Wolf.class) +
                " | " + getAnimalImage(Boa.class) + " - " + countAnimalsBySpecies(Boa.class) +
                " | " + getAnimalImage(Bear.class) + " - " + countAnimalsBySpecies(Bear.class) +
                " | " + getAnimalImage(Eagle.class) + " - " + countAnimalsBySpecies(Eagle.class) +
                " | " + getAnimalImage(Boar.class) + " - " + countAnimalsBySpecies(Boar.class) +
                " | " + getAnimalImage(Fox.class) + " - " + countAnimalsBySpecies(Fox.class) +
                " | " + getAnimalImage(Horse.class) + " - " + countAnimalsBySpecies(Horse.class) +
                " | " + getAnimalImage(Rabbit.class) + " - " + countAnimalsBySpecies(Rabbit.class) +
                " | " + getAnimalImage(Bull.class) + " - " + countAnimalsBySpecies(Bull.class) +
                " | " + getAnimalImage(Deer.class) + " - " + countAnimalsBySpecies(Deer.class) +
                " | " + getAnimalImage(Goat.class) + " - " + countAnimalsBySpecies(Goat.class) +
                " | " + getAnimalImage(Mouse.class) + " - " + countAnimalsBySpecies(Mouse.class) +
                " | " + getAnimalImage(Sheep.class) + " - " + countAnimalsBySpecies(Sheep.class) +
                " | " + getAnimalImage(Duck.class) + " - " + countAnimalsBySpecies(Duck.class) +
                " | " + getAnimalImage(Worm.class) + " - " + countAnimalsBySpecies(Worm.class) +
                " | " + getAnimalImage(Grass.class) + " - " + countAnimalsBySpecies(Grass.class));
    }

    public int countAnimalsBySpecies(AnnotatedElement annotatedElement) {
        List<? super Animal> animalsBySpecies = new ArrayList<>();
        for (Location[] row : grid) {
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

    public String getAnimalImage(Class<? extends Species> speciesClass) {
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
