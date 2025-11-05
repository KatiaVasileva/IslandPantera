package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Predator;

@SpeciesCharacteristics(name = "Fox", weight = 8, maxPerCell = 30, speed = 2, foodRequired = 2)
public class Fox extends Predator {
    public Fox() {
        super("Fox", 8, 30,2, 2);
    }
}
