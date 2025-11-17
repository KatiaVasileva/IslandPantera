package com.javarush.island.vasileva.entity.map;

import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Location {
    private int x;
    private int y;
    private final List<Animal> animals = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();

    private final Object animalLock = new Object();
    private final Object plantLock = new Object();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addAnimal(Animal animal) {
        synchronized (animalLock) {
            animals.add(animal);
            animal.setLocation(this);
        }
    }

    public void addPlant(Plant plant) {
        synchronized (plantLock) {
            plants.add(plant);
            plant.setLocation(this);
        }
    }

    public List<Animal> getAnimals() {
        synchronized (animalLock) {
            return new ArrayList<>(animals);
        }
    }

    public List<Plant> getPlants() {
        synchronized (plantLock) {
            return new ArrayList<>(plants);
        }
    }

    public void removeAnimal(Animal animal) {
        synchronized (animalLock) {
            animals.remove(animal);
        }
    }

    public synchronized void removePlant(Plant plant) {
        synchronized (plantLock) {
            plants.remove(plant);
        }
    }
}
