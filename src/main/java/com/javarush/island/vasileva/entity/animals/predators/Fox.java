package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Predator;

@OrganismData(name = "Fox", maxWeight = 8, maxPerCell = 30, speed = 2, foodRequired = 2, image = "\uD83E\uDD8A")
public class Fox extends Predator {
}
