package com.javarush.island.vasileva.entity;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadLocalRandom;

import static com.javarush.island.vasileva.config.Setting.ORGANISM_PLACEMENT_CYCLES;
import static com.javarush.island.vasileva.config.Setting.getData;

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

    public void placeOrganisms(Island island) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        OrganismData data = getData(this);
        int maxPerCell = data.maxPerCell();
        int counter = ORGANISM_PLACEMENT_CYCLES;

        while (counter > 0) {
            int count = ThreadLocalRandom.current().nextInt(1, maxPerCell + 1);
            int startX = ThreadLocalRandom.current().nextInt(island.getHeight());
            int startY = ThreadLocalRandom.current().nextInt(island.getWidth());
            for (int i = 0; i < count; i++) {
                Location location = island.getLocation(startX, startY);
                if (location != null) {
                    Organism organism = this.getClass().getConstructor().newInstance();
                    if (organism instanceof Animal animal) {
                        location.addAnimal(animal);
                        animal.setLocation(location);
                    }
                    if (organism instanceof Plant plant) {
                        location.addPlant(plant);
                        plant.setLocation(location);
                    }


                }
            }
            counter--;
        }
    }
}
