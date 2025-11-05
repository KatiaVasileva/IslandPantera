package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Mouse", weight = 0.05, maxPerCell = 500, speed = 1, foodRequired = 0.01)
public class Mouse extends Herbivore {
    public Mouse() {
        super("Mouse", 0.05, 500, 1, 0.01);
    }
}
