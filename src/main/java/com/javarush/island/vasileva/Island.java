package com.javarush.island.vasileva;

import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import lombok.Getter;
import lombok.Setter;

import static com.javarush.island.vasileva.config.Setting.*;

@Getter
@Setter
public class Island {
    private final Location[][] grid;
    private final int width = WIDTH;
    private final int height = HEIGHT;

    public Island(int width, int height) {
        grid = new Location[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
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
}
