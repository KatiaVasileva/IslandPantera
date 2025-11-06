package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Rabbit", weight = 2, maxPerCell = 150, speed = 2, foodRequired = 0.45, image = "\uD83D\uDC07")
public class Rabbit extends Herbivore {
    public Rabbit() {
        super("Rabbit", 2, 150, 2, 0.45, "\uD83D\uDC07");
    }
}
