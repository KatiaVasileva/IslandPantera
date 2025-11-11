package com.javarush.island.vasileva.entity;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;

@Getter
@Setter
public abstract class Organism {
    private transient OrganismData organismData;
    private boolean isALive = true;

    public Organism() {
        this.organismData = this.getClass().getAnnotation(OrganismData.class);
        if (organismData == null) {
            throw new RuntimeException("Class " + this.getClass().getSimpleName() + " must have @OrganismData annotation");
        }
    }

    public String getName() {
        return organismData.name();
    }

    public double getWeight() {
        return organismData.weight();
    }

    public int getSpeed() {
        return organismData.speed();
    }

    public double getFoodRequired() {
        return organismData.foodRequired();
    }

    public int getMaxPerCell() {
        return organismData.maxPerCell();
    }

    public String getImage() {
        return organismData.image();
    }

    public abstract void placeOrganisms(Island island) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException;
}
