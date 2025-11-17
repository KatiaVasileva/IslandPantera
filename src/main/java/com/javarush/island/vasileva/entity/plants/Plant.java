package com.javarush.island.vasileva.entity.plants;

import com.javarush.island.vasileva.api.entity.Reproducible;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.api.entity.Eatable;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Plant extends Organism implements Eatable, Reproducible {
    private Location location;

    @Override
    public void reproduce() {

    }
}
