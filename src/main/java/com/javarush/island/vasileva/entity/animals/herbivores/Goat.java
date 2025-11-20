package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Goat", maxWeight = 60, maxPerCell = 140, speed = 3, foodRequired = 10, eatable = true, image = "\uD83D\uDC10")
public class Goat extends Herbivore {
}
