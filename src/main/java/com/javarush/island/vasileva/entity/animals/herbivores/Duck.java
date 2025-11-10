package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Duck", weight = 1, maxPerCell = 2200, speed = 4, foodRequired = 0.15, image = "\uD83E\uDD86")
public class Duck extends Herbivore {
    public Duck() {
        super("Duck", 1, 200, 4, 0.15, "\uD83E\uDD86");
    }
}
