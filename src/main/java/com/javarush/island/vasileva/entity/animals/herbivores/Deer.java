package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Deer", weight = 300, maxPerCell = 20, speed = 4, foodRequired = 50, image = "\uD83E\uDD8C")
public class Deer extends Herbivore {
    public Deer() {
        super("Deer", 300, 20, 4, 50,"\uD83E\uDD8C");
    }
}
