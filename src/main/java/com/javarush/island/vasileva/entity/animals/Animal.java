package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.entity.Species;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Animal extends Species {
    private int speed;
    private double foodRequired;
    private Location location;

    public Animal() {
    }

    public Animal(String name, double weight, int maxPerCell, int speed, double foodRequired) {
        super(name, weight, maxPerCell);
        this.speed = speed;
        this.foodRequired = foodRequired;
    }

    public abstract void eat();

    public abstract void reproduce();

    public abstract void move();

}
