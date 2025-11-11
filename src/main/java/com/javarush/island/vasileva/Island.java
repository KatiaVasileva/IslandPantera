package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.javarush.island.vasileva.config.Setting.*;
import static com.javarush.island.vasileva.util.Statistics.printStatistics;

@Getter
@Setter
public class Island {
    private final Location[][] grid;
    private final int width = WIDTH;
    private final int height = HEIGHT;
    
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);
    static int counter;

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
        scheduledExecutorService.scheduleWithFixedDelay(this::processAnimals, 0, tickDuration, TimeUnit.MILLISECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(this::getStatistics, 0, tickDuration, TimeUnit.MILLISECONDS);
    }

    public void processAnimals() {
        for (Location[] row : grid) {
            for (Location location : row) {
                for (Animal animal : location.getAnimals()) {
                    executorService.submit(() -> {
                        if (animal.isALive()) {
                            animal.eat();
                            animal.reproduce();
                            animal.move(this);
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
        System.out.println(counter++);
        printStatistics(totalAnimals, this);
    }

}
