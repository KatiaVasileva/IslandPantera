package com.javarush.island.vasileva;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SpeciesCharacteristics {
    String name();
    double weight();
    int speed() default 0;
    double foodRequired() default 0;
    int maxPerCell();
    String image();
}
