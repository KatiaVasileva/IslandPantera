package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.javarush.island.vasileva.config.Setting.*;
import static com.javarush.island.vasileva.util.Debug.logEat;

@Getter
@Setter
public abstract class Animal extends Organism {

    public void eat() {
        Location loc = getLocation();
        if (loc == null) return;

        List<Organism> food = findFood();

        for (Organism item : food) {
            if (!canEat(item)) continue;

            synchronized (loc) {
                synchronized (item) {
                    if (!isFoodAvailable(item)) continue;
                    consumeFood(item);
                    logEat(this, item, loc);
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
    }

    public void move(Island island) {
        OrganismData data = getData(this);

        Location loc = getLocation();
        if (loc == null) return;

        Location newLoc = loc.getNewLocation(island, data);
        if (!loc.isMoveValid(newLoc, data)) return;

        Location[] lockOrder = loc.getLockOrder(newLoc);

        performMove(lockOrder[0], lockOrder[1], loc, newLoc);
        age++;
    }
}
