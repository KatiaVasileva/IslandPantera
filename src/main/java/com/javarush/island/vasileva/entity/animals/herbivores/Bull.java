package com.javarush.island.vasileva.entity.animals.herbivores;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Herbivore;

@OrganismData(name = "Bull", weight = 700, maxPerCell = 10, speed = 3, foodRequired = 100, eatable = true, image = "\uD83D\uDC03")
public class Bull extends Herbivore {
}
