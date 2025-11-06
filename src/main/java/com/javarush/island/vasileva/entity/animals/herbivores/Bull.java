package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@SpeciesCharacteristics(name = "Bull", weight = 700, maxPerCell = 10, speed = 3, foodRequired = 100, image = "\uD83D\uDC03")
public class Bull extends Herbivore {
    public Bull() {
        super("Bull", 700, 10, 3, 100, "\uD83D\uDC03");
    }
}
