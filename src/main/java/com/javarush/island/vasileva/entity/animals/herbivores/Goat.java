package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Goat", weight = 60, maxPerCell = 140, speed = 3, foodRequired = 10)
public class Goat extends Herbivore {
    public Goat() {
        super("Goat", 60, 140, 3, 10);
    }
}
