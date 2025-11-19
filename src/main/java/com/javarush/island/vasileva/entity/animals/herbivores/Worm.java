package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Worm", weight = 0.01, maxPerCell = 1000, eatable = true, image = "\uD83D\uDC1B")
public class Worm extends Herbivore {
}
