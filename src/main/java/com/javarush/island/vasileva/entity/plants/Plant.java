package com.javarush.island.vasileva.entity.plants;

import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.api.interfaces.Eatable;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Plant extends Organism implements Eatable {
    private Location location;
}
