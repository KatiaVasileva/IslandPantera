package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Horse", weight = 400, maxPerCell = 20, speed = 4, foodRequired = 60)
public class Horse extends Herbivore {
    public Horse() {
        super("Horse", 400, 20,4, 60);
    }
}
