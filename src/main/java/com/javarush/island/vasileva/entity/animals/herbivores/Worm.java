package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Worm", weight = 0.01, maxPerCell = 1000, image = "\uD83D\uDC1B")
public class Worm extends Herbivore {
    public Worm() {
        super("Worm", 0.01, 1000, 0, 0, "\uD83D\uDC1B");
    }
}
