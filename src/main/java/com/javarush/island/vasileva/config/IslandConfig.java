package com.javarush.island.vasileva.config;

import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.service.*;
import com.javarush.island.vasileva.view.ConsoleRenderer;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;

import static com.javarush.island.vasileva.config.Setting.TYPES;

@Getter
public class IslandConfig {
    private final Island island;

    public IslandConfig(Island island) {
        this.island = island;
    }

    public void initIsland(Island island) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Class<?> type : TYPES) {
            Organism org = (Organism) type.getConstructor().newInstance();
            org.placeOrganisms(island);
        }
    }

    public void configureServices(SimulationEngine engine, ConsoleRenderer consoleRenderer) {
        engine.addService(new EatingService(island, engine.getWorkerPool()));
        engine.addService(new MovementService(island, engine.getWorkerPool()));
        engine.addService(new ReproductionService(island, engine.getWorkerPool()));
        engine.addService(new PlantGrowthService(island));
        engine.addService(new StatisticsService(island, consoleRenderer));
    }
}
