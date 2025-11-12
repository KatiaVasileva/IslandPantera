package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Duck", weight = 1, maxPerCell = 200, speed = 4, foodRequired = 0.15, image = "\uD83E\uDD86")
public class Duck extends Herbivore {
}
