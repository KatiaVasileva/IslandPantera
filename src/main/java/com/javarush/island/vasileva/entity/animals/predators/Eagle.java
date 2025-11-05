package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Predator;

@SpeciesCharacteristics(name = "Eagle", weight = 6, maxPerCell = 20, speed = 3, foodRequired = 1)
public class Eagle extends Predator {
    public Eagle() {
        super("Eagle", 6, 20, 3, 1);
    }
}
