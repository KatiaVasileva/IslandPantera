package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Getter
@Setter
public abstract class Animal extends Organism {

    public void eat() {
        Location loc = getLocation();
        if (loc == null) {
            return;
        }

        List<Organism> food = findFood();

        for (Organism item : food) {
            synchronized (loc) {
                synchronized (item) {
                    if (!canEat(item)) {
                        looseWeight();
                        return;
                    }
                    if (!isFoodAvailable(item)) {
                        continue;
                    }
                    consumeFood(item);
                    setWeightsAfterEating(item);
                    if (item.getWeight() <= 0) {
                        item.die();
                    }
                    return;
                }
            }
        }
    }

    public void reproduce() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (cannotReproduce()) return;

        Location loc = getLocation();
        if (loc == null) return;

        synchronized (loc) {
            if (cannotReproduce()) return;

            List<Organism> sameSpecies = findPotentialPartners();

            if (hasSufficientPartners(sameSpecies)) {
                tryReproduce();
            }
        }
        age++;
    }

    public void move(Island island) {
        Location loc = getLocation();
        if (loc == null) return;

        Location newLoc = loc.getNewLocation(island, this);
        if (!loc.isMoveValid(newLoc, this)) return;

        Location[] lockOrder = loc.getLockOrder(newLoc);

        performMove(lockOrder[0], lockOrder[1], loc, newLoc);
    }
}
