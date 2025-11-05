package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Predator;

@SpeciesCharacteristics(name = "Boa", weight = 15, maxPerCell = 30, speed = 1, foodRequired = 3)
public class Boa extends Predator {
    public Boa() {
        super("Boa", 15, 30,1, 3);
    }
}
