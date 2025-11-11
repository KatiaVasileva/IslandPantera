package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Deer", weight = 300, maxPerCell = 20, speed = 4, foodRequired = 50, image = "\uD83E\uDD8C")
public class Deer extends Herbivore {
}
