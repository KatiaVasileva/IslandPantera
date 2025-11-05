package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.SpeciesCharacteristics;
import com.javarush.island.vasileva.entity.animals.Predator;

@SpeciesCharacteristics(name = "Boar", weight = 400, maxPerCell = 50, speed = 2, foodRequired = 50)
public class Boar extends Predator {
    public Boar() {
        super("Boar", 400, 50,2, 50);
    }
}
