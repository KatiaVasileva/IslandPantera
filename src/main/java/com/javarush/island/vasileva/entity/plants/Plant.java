package com.javarush.island.vasileva.entity.plants;

import com.javarush.island.vasileva.entity.Species;

public abstract class Plant extends Species {
    public Plant() {
    }

    public Plant(String name, double weight, int maxPerCell) {
        super(name, weight, maxPerCell);
    }
}
