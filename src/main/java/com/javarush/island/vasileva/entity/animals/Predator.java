package com.javarush.island.vasileva.entity.animals;

public class Predator extends Animal {
    public Predator() {
    }

    public Predator(String name,
                    double weight,
                    int maxPerCell,
                    int speed,
                    double foodRequired,
                    String image) {
        super(name, weight, maxPerCell, speed, foodRequired, image);
    }

    @Override
    public void eat() {
//        System.out.println("Predator is eating");
    }

    @Override
    public void reproduce() {
//        System.out.println("Predator is reproducing");
    }


}
