package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.api.interfaces.Eatable;
import com.javarush.island.vasileva.config.EatingChances;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.vasileva.config.Setting.*;

@Getter
@Setter
public abstract class Animal extends Organism {
    private Location location;
    protected int age = 0;
    protected volatile boolean hasReproduced = false;

    /* ========================== EAT ================================ */
    public void eat() {
        Location loc = getLocation();
        if (loc == null) return;

        List<Eatable> food = collectFood(loc);

        for (Eatable item : food) {
            if (!canEat(item)) continue;

            synchronized (loc) {
                synchronized (item) {
                    if (!isItemAvailable(item)) {
                        continue;
                    }
                    consumeItem(item, loc);
                    System.out.printf("%s съел %s в [%d,%d]%n",
                            getData(this).name() + this.getId(), getData((Organism) item).name() + ((Organism) item).getId(), loc.getX(), loc.getY());
                    return;
                }
            }
        }
    }

    private List<Eatable> collectFood(Location loc) {
        List<Eatable> food = new ArrayList<>(loc.getPlants());
        for (Animal animal : loc.getAnimals()) {
            if (animal.isALive() && animal instanceof Eatable eatable) {
                food.add(eatable);
            }
        }
        return food;
    }

    private boolean canEat(Eatable item) {
        double chance = EatingChances.getChances(this.getClass(), item.getClass());
        return chance > 0 && Math.random() < chance;
    }

    private boolean isItemAvailable(Eatable item) {
        return ((Organism) item).isALive();
    }

    private void consumeItem(Eatable item, Location loc) {
        if (item instanceof Animal prey) {
            prey.die();
            loc.removeAnimal(prey);
        } else if (item instanceof Plant targetPlant) {
            targetPlant.die();
            loc.removePlant(targetPlant);
        }
    }

    /* ========================== REPRODUCE  ================================ */
    public void reproduce() {
        if (age < 5 || hasReproduced || !isALive()) return;

        Location loc = getLocation();
        if (loc == null) return;

        synchronized (loc) {
            if (age < 5 || hasReproduced || !isALive()) return;

            List<Animal> sameSpecies = loc.getAnimals().stream()
                    .filter(a -> a.getClass() == this.getClass() && a.isALive())
                    .toList();

            if (sameSpecies.size() >= 2 && sameSpecies.size() < getData(this).maxPerCell()) {
                try {
                    Animal offspring = this.getClass().getDeclaredConstructor().newInstance();
                    offspring.age = 0;
                    loc.addAnimal(offspring);
                    hasReproduced = true;

                    System.out.printf("%s родил потомка %s в [%d,%d]%n",
                            getData(this).name() + this.getId(), getData(offspring).name() + offspring.getId(), loc.getX(), loc.getY());
                } catch (Exception e) {
                    System.out.println("Ошибка при размножении: " + e.getMessage());
                }
            }
        }
    }

    /* ========================== MOVE ====================================== */

    public void move(Island island) {
        OrganismData data = getData(this);

        Location currentLocation = getLocation();
        if (currentLocation == null) return;

        Location newLocation = getNewLocation(island, currentLocation, data);
        if (!isMoveValid(currentLocation, newLocation, data)) return;

        Location[] lockOrder = getLockOrder(currentLocation, newLocation);

        performMove(lockOrder[0], lockOrder[1], currentLocation, newLocation);
        age++;
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
