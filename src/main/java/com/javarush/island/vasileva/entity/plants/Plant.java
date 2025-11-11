package com.javarush.island.vasileva.entity.plants;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.api.interfaces.Eatable;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.vasileva.config.Setting.getData;

@Getter
@Setter
public abstract class Plant extends Organism implements Eatable {
    private Location location;

    @Override
    public void placeOrganisms(Island island) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        OrganismData data = getData(this);
        int maxPerCell = data.maxPerCell();

        int count = ThreadLocalRandom.current().nextInt(1, maxPerCell + 1);
        for (Location[] row : island.getGrid()) {
            for (Location location : row) {
                for (int i = 0; i < count; i++) {
                    if (location != null) {
                        Plant plant = this.getClass().getConstructor().newInstance();
                        location.addPlant(plant);
                        plant.setLocation(location);
                    }
                }
            }
        }
    }
}
