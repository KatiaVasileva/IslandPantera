package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Rabbit", weight = 2, maxPerCell = 150, speed = 2, foodRequired = 0.45, image = "\uD83D\uDC07")
public class Rabbit extends Herbivore {
}
