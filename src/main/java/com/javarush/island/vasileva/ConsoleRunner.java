package com.javarush.island.vasileva;

import com.javarush.island.vasileva.config.Setting;

public class ConsoleRunner {

    public static void main(String[] args) {

        Island island = new Island(Setting.WIDTH, Setting.HEIGHT);

        Setting.init(island);

        System.out.println("start simulation");
        island.startSimulation(Setting.TICK_DURATION);

    }
}
