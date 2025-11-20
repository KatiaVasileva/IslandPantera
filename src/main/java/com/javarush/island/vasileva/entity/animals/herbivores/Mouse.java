package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Mouse", maxWeight = 0.05, maxPerCell = 500, speed = 1, foodRequired = 0.01, eatable = true, image = "\uD83D\uDC01")
public class Mouse extends Herbivore {
}
