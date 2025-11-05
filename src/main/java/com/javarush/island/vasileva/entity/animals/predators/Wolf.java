package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Predator;

@SpeciesCharacteristics(name = "Wolf", weight = 50, speed = 3, foodRequired = 8, maxPerCell = 30)
public class Wolf extends Predator {

    public Wolf() {
        super("Wolf", 50, 30, 3, 8);
    }

}
