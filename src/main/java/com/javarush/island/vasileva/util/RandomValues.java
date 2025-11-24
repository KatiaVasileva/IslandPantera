package com.javarush.island.vasileva.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomValues {
    private RandomValues() {}

    public static double getRandomWeight(double maxWeight, double minWeghtFactor) {
        return ThreadLocalRandom.current().nextDouble(maxWeight * minWeghtFactor, maxWeight);
    }

    public static int getRandomInt(int value) {
        return ThreadLocalRandom.current().nextInt(value);
    }

    public static int getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
