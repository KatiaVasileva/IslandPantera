package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.api.interfaces.Eatable;

public class Herbivore extends Animal implements Eatable {
    public Herbivore() {
    }

    public Herbivore(String name,
                     double weight,
                     int maxPerCell,
                     int speed,
                     double foodRequired,
                     String image) {
        super(name, weight, maxPerCell, speed, foodRequired, image);
    }

    @Override
    public void eat() {
//        System.out.println("Herbivore is eating");
    }

    @Override
    public void reproduce() {
//        System.out.println("Herbivore is reproducing");
    }


}
