package com.javarush.island.vasileva.entity.map;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import static com.javarush.island.vasileva.config.Setting.DIRECTIONS;

@Getter
@Setter
public class Location {
    private int x;
    private int y;
    private final List<Organism> organisms = new ArrayList<>();
    private Map<String, List<Organism>> species = new HashMap<>();

    private final ReentrantLock lock = new ReentrantLock();
    private final Object organismLock = new Object();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.species = getOrganisms().stream().collect(Collectors.groupingBy(Organism::getName));
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

    public void addOrganism2(Organism organism) {
        if (organism == null) {
            throw new IllegalArgumentException("Organism cannot be null");
        }
        synchronized (organismLock) {
            for (Map.Entry<String, List<Organism>> entry : species.entrySet()) {
                if (entry.getKey().contains(organism.getName())) {
                    entry.getValue().add(organism);
                } else {
                    entry.getValue().add(organism);
                    species.put(organism.getName(), entry.getValue());
                }
            }
            organism.setLocation(this);
        }
    }

    public List<Organism> getOrganisms() {
        synchronized (organismLock) {
            return new ArrayList<>(organisms);
        }
    }

    public Map<String, List<Organism>> getOrganisms2() {
        synchronized (organismLock) {
            return new HashMap<>(species);
        }
    }

    public synchronized void removeOrganism(Organism organism) {
        synchronized (organismLock) {
            organisms.remove(organism);
        }
    }

    public synchronized void removeOrganism2(Organism organism) {
        synchronized (organismLock) {
            for (Map.Entry<String, List<Organism>> entry : species.entrySet()) {
                if (entry.getKey().contains(organism.getName())) {
                    entry.getValue().remove(organism);
                } else {
                    System.out.println("Not removed");
                }
            }
        }
    }

    public Location getNewLocation(Island island, OrganismData data) {
        int[] direction = DIRECTIONS[ThreadLocalRandom.current().nextInt(4)];

        int newX = getX() + (direction[0] * ThreadLocalRandom.current().nextInt(data.speed() + 1));
        int newY = getY() + (direction[1] * ThreadLocalRandom.current().nextInt(data.speed() + 1));

        if (newX < 0 || newX > island.getWidth() || newY < 0 || newY > island.getHeight()) {
            return this;
        }
        return island.getLocation(newX, newY);
    }

    public boolean isMoveValid(Location newLocation, OrganismData data) {
        if (newLocation == null) return false;
        if (this == newLocation) return false;
        System.out.println(newLocation.getSpecies().get(data.name()));
        return newLocation.getSpecies().get(data.name()).size() < data.maxPerCell();
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
