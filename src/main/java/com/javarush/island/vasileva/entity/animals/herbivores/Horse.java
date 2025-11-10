package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Horse", weight = 400, maxPerCell = 20, speed = 4, foodRequired = 60, image = "\uD83D\uDC0E")
public class Horse extends Herbivore {
    public Horse() {
        super("Horse", 400, 20, 4, 60, "\uD83D\uDC0E");
    }
}
