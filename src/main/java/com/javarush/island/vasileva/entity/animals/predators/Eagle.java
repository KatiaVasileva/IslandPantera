package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Predator;

@OrganismData(name = "Eagle", weight = 6, maxPerCell = 20, speed = 3, foodRequired = 1, image = "\uD83E\uDD85")
public class Eagle extends Predator {
}
