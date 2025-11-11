package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Horse", weight = 400, maxPerCell = 20, speed = 4, foodRequired = 60, image = "\uD83D\uDC0E")
public class Horse extends Herbivore {
}
