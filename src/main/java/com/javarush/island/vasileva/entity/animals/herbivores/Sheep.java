package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Sheep", weight = 70, maxPerCell = 140, speed = 3, foodRequired = 15, eatable = true, image = "\uD83D\uDC11")
public class Sheep extends Herbivore {
}
