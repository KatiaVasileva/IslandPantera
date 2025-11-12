package com.javarush.island.vasileva.view;

import com.javarush.island.vasileva.entity.animals.herbivores.*;
import com.javarush.island.vasileva.entity.animals.predators.*;
import com.javarush.island.vasileva.entity.plants.Grass;

import java.util.HashMap;
import java.util.Map;

import static com.javarush.island.vasileva.config.Setting.*;

public class SymbolMap {
    public static final Map<Class<?>, String> SYMBOLS = new HashMap<>();
    private static final Map<Class<?>, String> ABBREVS = new HashMap<>();

    static {
        SYMBOLS.put(Wolf.class, WOLF_IMAGE);
        SYMBOLS.put(Boa.class, BOA_IMAGE);
        SYMBOLS.put(Fox.class, FOX_IMAGE);
        SYMBOLS.put(Bear.class, BEAR_IMAGE);
        SYMBOLS.put(Eagle.class, EAGLE_IMAGE);
        SYMBOLS.put(Horse.class, HORSE_IMAGE);
        SYMBOLS.put(Deer.class, DEER_IMAGE);
        SYMBOLS.put(Rabbit.class, RABBIT_IMAGE);
        SYMBOLS.put(Mouse.class, MOUSE_IMAGE);
        SYMBOLS.put(Goat.class, GOAT_IMAGE);
        SYMBOLS.put(Sheep.class, SHEEP_IMAGE);
        SYMBOLS.put(Boar.class, BOAR_IMAGE);
        SYMBOLS.put(Bull.class, BULL_IMAGE);
        SYMBOLS.put(Duck.class, DUCK_IMAGE);
        SYMBOLS.put(Worm.class, WORM_IMAGE);
        SYMBOLS.put(Grass.class, GRASS_IMAGE);


        ABBREVS.put(Wolf.class, "В");
        ABBREVS.put(Boa.class, "Уд");
        ABBREVS.put(Fox.class, "Лис");
        ABBREVS.put(Bear.class, "Мед");
        ABBREVS.put(Eagle.class, "Ор");
        ABBREVS.put(Horse.class, "Лош");
        ABBREVS.put(Deer.class, "Ол");
        ABBREVS.put(Rabbit.class, "Кр");
        ABBREVS.put(Mouse.class, "М");
        ABBREVS.put(Goat.class, "Коз");
        ABBREVS.put(Sheep.class, "Овц");
        ABBREVS.put(Boar.class, "Каб");
        ABBREVS.put(Bull.class, "Буй");
        ABBREVS.put(Duck.class, "Ут");
        ABBREVS.put(Worm.class, "Гус");
        ABBREVS.put(Grass.class, "Тр");
    }

    public static String getSymbol(Object obj) {
        return SYMBOLS.getOrDefault(obj.getClass(), "?");
    }

    public static String getSymbol(Class<?> clazz) {
        return SYMBOLS.getOrDefault(clazz, "?");
    }

    public static String getAbbrev(Object obj) {
        return ABBREVS.getOrDefault(obj.getClass(), "?");
    }

    public static String getAbbrev(Class<?> clazz) {
        return ABBREVS.getOrDefault(clazz, "?");
    }
}
