package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Goat", weight = 60, maxPerCell = 140, speed = 3, foodRequired = 10, image = "\uD83D\uDC10")
public class Goat extends Herbivore {
    public Goat() {
        super("Goat", 60, 140, 3, 10, "\uD83D\uDC10");
    }
}
