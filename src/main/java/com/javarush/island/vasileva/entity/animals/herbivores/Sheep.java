package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Sheep", weight = 70, maxPerCell = 140, speed = 3, foodRequired = 15, image = "\uD83D\uDC11")
public class Sheep extends Herbivore {
    public Sheep() {
        super("Sheep", 70, 140, 3, 15, "\uD83D\uDC11");
    }
}
