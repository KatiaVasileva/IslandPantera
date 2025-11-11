package com.javarush.island.vasileva;

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
    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setLocation(this);
    }

    public synchronized void addPlant(Plant plant) {
        plants.add(plant);
        plant.setLocation(this);
    }

    public synchronized List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    public synchronized List<Plant> getPlants() {
        return new ArrayList<>(plants);
    }

    public synchronized void removeAnimal(Animal animal) {
        animals.remove(animal);
    }
}
