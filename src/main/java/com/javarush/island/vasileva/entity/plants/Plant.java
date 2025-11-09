package com.javarush.island.vasileva.entity.plants;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Plant extends Organism {
    private Location location;

    public Plant() {
    }

    public Plant(String name, double weight, int maxPerCell, String image) {
        super(name, weight, maxPerCell, image);
    }

    @Override
    public void placeOrganisms(Island island) {
        int count = ThreadLocalRandom.current().nextInt(2, this.getMaxPerCell());
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (int i = 0; i < count; i++) {
                    if (location != null) {
                        location.addPlant(this);
                    }
                }
            }
        }

    }
}
