package com.javarush.island.vasileva.api.services;

import com.javarush.island.vasileva.api.entity.Eatable;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.map.Location;

import java.util.List;

public interface FoodService {

    List<Eatable> collectFood(Location loc);

    boolean canEat(Animal animal, Eatable item);

    boolean isItemAvailable(Eatable item);

    void consumeItem(Eatable item, Location loc);

}
