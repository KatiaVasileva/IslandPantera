package com.javarush.island.vasileva.entity.animals.predators;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.animals.Predator;

import static com.javarush.island.vasileva.config.Setting.WOLF_IMAGE;

@OrganismData(name = "Wolf", weight = 50, maxPerCell = 30, speed = 3, foodRequired = 8, image = WOLF_IMAGE)
public class Wolf extends Predator {
}
