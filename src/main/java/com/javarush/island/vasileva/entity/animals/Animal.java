package com.javarush.island.vasileva.entity.animals;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.config.Setting;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ThreadLocalRandom;

@Getter
@Setter
public abstract class Animal extends Organism {
    private int speed;
    private double foodRequired;
    private Location location;

    public Animal() {
    }

    public Animal(String name, double weight, int maxPerCell, int speed, double foodRequired, String image) {
        super(name, weight, maxPerCell, image);
        this.speed = speed;
        this.foodRequired = foodRequired;
    }

    @Override
    public void placeOrganisms(Island island) {
        int count = ThreadLocalRandom.current().nextInt(1, this.getMaxPerCell());
        int startX = (int) (Math.random() * Setting.WIDTH);
        int startY = (int) (Math.random() * Setting.HEIGHT);
        for (int i = 0; i < count; i++) {
            Location location = island.getLocation(startX, startY);
            if (location != null) {
                location.addAnimal(this);
            }
        }
    }

    public abstract void eat();

    public abstract void reproduce();

    public abstract void move();

}
