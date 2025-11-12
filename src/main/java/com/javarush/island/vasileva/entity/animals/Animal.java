package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.vasileva.config.Setting.*;

@Getter
@Setter
public abstract class Animal extends Organism {
    private Location location;

    public abstract void eat();

    public abstract void reproduce();

    public void move(Island island) {
        OrganismData data = getData(this);

        Location currentLocation = getLocation();
        if (currentLocation == null) return;

        Location newLocation = getNewRandomLocation(island, currentLocation, data);
        if (newLocation == null || newLocation.getAnimals().size() >= data.maxPerCell()) return;
        if (currentLocation == newLocation) return;

        Location firstLock = (currentLocation.getX() < newLocation.getX()) ? currentLocation : newLocation;
        if (currentLocation.getX() == newLocation.getX()) {
            firstLock = (currentLocation.getY() < newLocation.getY()) ? currentLocation : newLocation;
        }
        Location secondLock = (firstLock == currentLocation) ? newLocation : firstLock;

        synchronized (firstLock) {
            synchronized (secondLock) {
                currentLocation.removeAnimal(this);
                newLocation.addAnimal(this);
                this.setLocation(newLocation);
            }
        }
    }

    public Location getNewRandomLocation(Island island, Location currentLocation, OrganismData data) {
        int[] direction = DIRECTIONS[ThreadLocalRandom.current().nextInt(4)];

        int newX = currentLocation.getX() + (direction[0] * ThreadLocalRandom.current().nextInt( data.speed() + 1));
        int newY = currentLocation.getY() + (direction[1] * ThreadLocalRandom.current().nextInt( data.speed() + 1));

        if (newX < 0 || newX > island.getWidth() || newY < 0 || newY > island.getHeight()) {
            return currentLocation;
        }
        return island.getLocation(newX, newY);
    }
}
