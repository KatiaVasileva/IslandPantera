package com.javarush.island.vasileva.entity.animals;

public class Herbivore extends Animal {
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
        System.out.println("Herbivore is eating");
    }

    @Override
    public void reproduce() {
        System.out.println("Herbivore is reproducing");
    }

    @Override
    public void move() {
        System.out.println("Herbivore is moving");
    }
}
