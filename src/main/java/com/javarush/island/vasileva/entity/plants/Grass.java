package com.javarush.island.vasileva.entity.plants;

import com.javarush.island.vasileva.SpeciesCharacteristics;

@SpeciesCharacteristics(name = "Grass", weight = 1, maxPerCell = 200)
public class Grass extends Plant {
    public Grass() {
        super("Grass", 1, 200);
    }
}
