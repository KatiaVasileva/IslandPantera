package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Horse", maxWeight = 400, maxPerCell = 20, speed = 4, foodRequired = 60, eatable = true, image = "\uD83D\uDC0E")
public class Horse extends Herbivore {
}
