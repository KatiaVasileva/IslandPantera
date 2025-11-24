package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Predator;

@OrganismData(name = "Boa", maxWeight = 15, maxPerCell = 30, speed = 1, foodRequired = 3, eatable = true, image = "\uD83D\uDC0D")
public class Boa extends Predator{
}
