package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.animals.herbivores.Horse;
import com.javarush.island.vasileva.entity.animals.predators.Wolf;
import com.javarush.island.vasileva.entity.plants.Grass;
import lombok.Getter;
import lombok.Setter;

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
                if (location.getPlants().size() <200) {
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
        List<Wolf> wolves = new ArrayList<>();
        List<Horse> horses = new ArrayList<>();
        for (Location[] row : grid) {
            for (Location location : row) {
                List<Animal> animals = location.getAnimals();
                for (Animal animal : animals) {
                    if (animal.isALive() && animal instanceof Wolf wolf) {
                        wolves.add(wolf);
                    }
                    if (animal.isALive() && animal instanceof Horse horse) {
                        horses.add(horse);
                    }
                }
                totalAnimals += location.getAnimals().size();
            }
        }
        System.out.println("Total animals: " + totalAnimals);
        System.out.println("Wolves: " + wolves.size());
        System.out.println("Horses: " + horses.size());
    }

}
