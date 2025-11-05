package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Sheep", weight = 70, maxPerCell = 140, speed = 3, foodRequired = 15)
public class Sheep extends Herbivore {
    public Sheep() {
        super("Sheep", 70, 140, 3, 15);
    }
}
