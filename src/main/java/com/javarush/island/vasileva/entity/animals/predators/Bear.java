package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Predator;

@SpeciesCharacteristics(name = "Bear", weight = 500, maxPerCell = 5, speed = 2, foodRequired = 80, image = "\uD83D\uDC3B")
public class Bear extends Predator {
    public Bear() {
        super("Bear", 500, 5, 2, 80, "\uD83D\uDC3B");
    }
}
