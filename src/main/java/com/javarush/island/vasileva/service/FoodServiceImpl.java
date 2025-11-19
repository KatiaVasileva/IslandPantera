package com.javarush.island.vasileva.service;

import com.javarush.island.vasileva.api.entity.Eatable;
import com.javarush.island.vasileva.api.services.FoodService;
import com.javarush.island.vasileva.config.EatingChances;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.entity.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {
    @Override
    public List<Eatable> collectFood(Location loc) {
        List<Eatable> food = new ArrayList<>(loc.getPlants());
        for (Animal animal : loc.getAnimals()) {
            if (animal.isALive() && animal instanceof Eatable eatable) {
                food.add(eatable);
            }
        }
        return food;
    }

    @Override
    public boolean canEat(Animal animal, Eatable item) {
        double chance = EatingChances.getChances(animal.getClass(), item.getClass());
        return chance > 0 && Math.random() < chance;
    }

    @Override
    public boolean isItemAvailable(Eatable item) {
        return ((Organism) item).isALive();
    }

    @Override
    public void consumeItem(Eatable item, Location loc) {
        if (item instanceof Animal prey) {
            prey.die();
            loc.removeAnimal(prey);
        } else if (item instanceof Plant targetPlant) {
            targetPlant.die();
            loc.removePlant(targetPlant);
        }
    }
}
