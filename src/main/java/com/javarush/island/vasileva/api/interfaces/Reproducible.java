package com.javarush.island.vasileva.api.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface Reproducible {
    void reproduce() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
