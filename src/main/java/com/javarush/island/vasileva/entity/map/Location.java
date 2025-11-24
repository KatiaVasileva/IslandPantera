package com.javarush.island.vasileva.entity.map;

import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static com.javarush.island.vasileva.config.Setting.DIRECTIONS;
import static com.javarush.island.vasileva.util.RandomValues.getRandomInt;

@Getter
@Setter
public class Location {
    private int x;
    private int y;
    private final List<Organism> organisms = new ArrayList<>();

    private final Object organismLock = new Object();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addOrganism(Organism organism) {
        if (organism == null) {
            throw new IllegalArgumentException("Organism cannot be null");
        }
        synchronized (organismLock) {
            organisms.add(organism);
            organism.setLocation(this);
        }
    }

    public List<Organism> getOrganisms() {
        synchronized (organismLock) {
            return new ArrayList<>(organisms);
        }
    }

    public void removeOrganism(Organism organism) {
        synchronized (organismLock) {
            organisms.remove(organism);
        }
    }

    public Map<Class<?>, List<Organism>> getSpecies() {
        synchronized (organismLock) {
            return new HashMap<>(getOrganisms().stream().collect(Collectors.groupingBy(Organism::getClass)));
        }
    }

    public Location getNewLocation(Island island, Organism organism) {
        int[] direction = DIRECTIONS[getRandomInt(DIRECTIONS.length)];

        int newX = getX() + (direction[0] * getRandomInt(organism.getSpeed() + 1));
        int newY = getY() + (direction[1] * getRandomInt(organism.getSpeed() + 1));

        if (newX < 0 || newX > island.getWidth() || newY < 0 || newY > island.getHeight()) {
            return this;
        }
        return island.getLocation(newX, newY);
    }

    public boolean isMoveValid(Location newLocation, Organism organism) {
        if (newLocation == null) return false;
        if (this == newLocation) return false;
        return getSpecies().get(organism.getClass()).size() < organism.getMaxPerCell();
    }

    public Location[] getLockOrder(Location otherLoc) {
        Location first = (getX() < otherLoc.getX()) ? this : otherLoc;
        if (getX() == otherLoc.getX()) {
            first = (getY() < otherLoc.getY()) ? this : otherLoc;
        }
        Location second = (first == this) ? otherLoc : this;
        return new Location[]{first, second};
    }
}
