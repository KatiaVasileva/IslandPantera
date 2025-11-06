package com.javarush.island.vasileva.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Species {
    private String name;
    private double weight;
    private int maxPerCell;
    private String image;
    private boolean isALive = true;

    public Species() {
    }

    public Species(String name, double weight, int maxPerCell, String image) {
        this.name = name;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.image = image;
    }
}
