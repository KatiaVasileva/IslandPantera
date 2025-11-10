package com.javarush.island.vasileva.entity.plants;

import com.javarush.island.vasileva.api.annotations.SpeciesCharacteristics;

@SpeciesCharacteristics(name = "Grass", weight = 1, maxPerCell = 200, image = "\uD83C\uDF3F")
public class Grass extends Plant {
    public Grass() {
        super("Grass", 1, 200, "\uD83C\uDF3F");
    }
}
