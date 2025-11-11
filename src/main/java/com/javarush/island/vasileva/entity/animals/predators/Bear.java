package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Predator;

@OrganismData(name = "Bear", weight = 500, maxPerCell = 5, speed = 2, foodRequired = 80, image = "\uD83D\uDC3B")
public class Bear extends Predator {
}
