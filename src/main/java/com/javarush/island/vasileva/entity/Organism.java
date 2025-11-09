package com.javarush.island.vasileva.entity;

import com.javarush.island.vasileva.Island;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Organism {
    private String name;
    private double weight;
    private int maxPerCell;
    private String image;
    private boolean isALive = true;

    public Organism() {
    }

    public Organism(String name, double weight, int maxPerCell, String image) {
        this.name = name;
        this.weight = weight;
        this.maxPerCell = maxPerCell;
        this.image = image;
    }

    public abstract void placeOrganisms(Island island);
}
