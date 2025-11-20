package com.javarush.island.vasileva.api.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface OrganismData {
    String name();
    double maxWeight();
    int speed() default 0;
    double foodRequired() default 0;
    int maxPerCell();
    boolean eatable() default false;
    String image();
}
