package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.vasileva.config.Setting.*;

@Getter
@Setter
public abstract class Animal extends Organism {
    private int speed;
    private double foodRequired;
    private Location location;

    public Animal() {
    }

    public Animal(String name, double weight, int maxPerCell, int speed, double foodRequired, String image) {
        super(name, weight, maxPerCell, image);
        this.speed = speed;
        this.foodRequired = foodRequired;
    }

    @Override
    public void placeOrganisms(Island island) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int count = ThreadLocalRandom.current().nextInt(1, this.getMaxPerCell());
        int startX = (int) (Math.random() * island.getWidth());
        int startY = (int) (Math.random() * island.getHeight());
        for (int i = 0; i < count; i++) {
            Location location = island.getLocation(startX, startY);
            if (location != null) {
                location.addAnimal(this.getClass().getConstructor().newInstance());
            }
        }
    }

    public abstract void eat();

    public abstract void reproduce();

    public void move(Island island) {
        Location currentLocation = getLocation();
        if (currentLocation == null) return;

        Location newLocation = getNewRandomLocation(island, currentLocation);
        if (newLocation == null || newLocation.getAnimals().size() >= this.getMaxPerCell()) return;
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

    public Location getNewRandomLocation(Island island, Location currentLocation) {
        int[] direction = DIRECTIONS[ThreadLocalRandom.current().nextInt(4)];

        int newX = currentLocation.getX() + (direction[0] * ThreadLocalRandom.current().nextInt( this.speed + 1));
        int newY = currentLocation.getY() + (direction[1] * ThreadLocalRandom.current().nextInt( this.speed + 1));

        if (newX < 0 || newX > island.getWidth() || newY < 0 || newY > island.getHeight()) {
            return currentLocation;
        }
        return island.getLocation(newX, newY);
    }

}
