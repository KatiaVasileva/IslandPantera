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

        Location newLocation = getNewLocation(island, currentLocation, data);
        if (!isMoveValid(currentLocation, newLocation, data)) return;

        Location[] lockOrder = getLockOrder(currentLocation, newLocation);

        performMove(lockOrder[0], lockOrder[1], currentLocation, newLocation);
    }

    private Location getNewLocation(Island island, Location currentLocation, OrganismData data) {
        int[] direction = DIRECTIONS[ThreadLocalRandom.current().nextInt(4)];

        int newX = currentLocation.getX() + (direction[0] * ThreadLocalRandom.current().nextInt(data.speed() + 1));
        int newY = currentLocation.getY() + (direction[1] * ThreadLocalRandom.current().nextInt(data.speed() + 1));

        if (newX < 0 || newX > island.getWidth() || newY < 0 || newY > island.getHeight()) {
            return currentLocation;
        }
        return island.getLocation(newX, newY);
    }

    private boolean isMoveValid(Location currentLocation, Location newLocation, OrganismData data) {
        if (newLocation == null) return false;
        if (currentLocation == newLocation) return false;
        return newLocation.getAnimals().size() < data.maxPerCell();
    }

    private Location[] getLockOrder(Location loc1, Location loc2) {
        Location first = (loc1.getX() < loc2.getX()) ? loc1 : loc2;
        if (loc1.getX() == loc2.getX()) {
            first = (loc1.getY() < loc2.getY()) ? loc1 : loc2;
        }
        Location second = (first == loc1) ? loc2 : loc1;
        return new Location[]{first, second};
    }

    private void performMove(Location firstLock, Location secondLock, Location from, Location to) {
        synchronized (firstLock) {
            synchronized (secondLock) {
                from.removeAnimal(this);
                to.addAnimal(this);
                this.setLocation(to);
            }
        }
    }
}
