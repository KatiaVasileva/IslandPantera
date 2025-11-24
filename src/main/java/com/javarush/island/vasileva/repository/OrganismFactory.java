package com.javarush.island.vasileva.repository;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Location;

import java.lang.reflect.InvocationTargetException;

import static com.javarush.island.vasileva.util.RandomValues.getRandomWeight;

public class OrganismFactory {
    private OrganismFactory() {}

    public static <T extends Organism> T createOrganism(Class<T> clazz, Location loc, double maxWeight, double minWeightFactor)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        T organism = clazz.getDeclaredConstructor().newInstance();
        organism.setWeight(getRandomWeight(maxWeight, minWeightFactor));
        loc.addOrganism(organism);
        organism.setLocation(loc);
        return organism;
    }
}
