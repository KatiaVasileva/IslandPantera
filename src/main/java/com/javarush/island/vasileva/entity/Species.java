package com.javarush.island.vasileva.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Species {
    private String name;
    private double weight;
    private int maxPerCell;
    private boolean isALive = true;

    public Species() {
    }

    public Species(String name, double weight, int maxPerCell) {
        this.name = name;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
    }

    public void die() {
        isALive = false;
    }
}
