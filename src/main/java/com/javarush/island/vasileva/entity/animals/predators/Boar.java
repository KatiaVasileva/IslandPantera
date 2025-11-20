package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Predator;

@OrganismData(name = "Boar", maxWeight = 400, maxPerCell = 50, speed = 2, foodRequired = 50, eatable = true, image = "\uD83D\uDC17")
public class Boar extends Predator {
}
