package com.javarush.island.vasileva.view;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static com.javarush.island.vasileva.config.Setting.*;

@Getter
@Setter
public class ConsoleRenderer {
    private final Island island;
    private boolean useSymbols = true;

    public ConsoleRenderer(Island island) {
        this.island = island;
    }

    public void render() {

        System.out.println("\n+" + "=".repeat(SHOW_WIDTH * (CELL_WIDTH + 1)));

        for (int x = 0; x < SHOW_HEIGHT; x++) {
            System.out.print("|");
            for (int y = 0; y < SHOW_WIDTH; y++) {
                Location location = island.getLocation(x, y);
                String cellContent = renderCell(location);
                System.out.print(cellContent);
                System.out.print("|");
            }
            System.out.println();
            System.out.println("+" + "=".repeat(SHOW_WIDTH * (CELL_WIDTH + 1)));
        }
    }

    private String renderCell(Location loc) {
        List<Organism> organisms = new ArrayList<>();
        List<Animal> animals = loc.getAnimals();
        List<Plant> plants = loc.getPlants();
        organisms.addAll(animals);
        organisms.addAll(plants);

        if (organisms.isEmpty()) {
            return " ".repeat(CELL_WIDTH);
        }

        String symbol = getMaxOrganismForCellRendering(organisms);

        if (symbol.length() > CELL_WIDTH) {
            symbol = symbol.substring(0, CELL_WIDTH);
        }
        return String.format("%-" + CELL_WIDTH + "s", symbol);
    }

    private String getMaxOrganismForCellRendering(List<Organism> organisms) {
        Map<Class<?>, Integer> organismsCount = new HashMap<>();

        for (Organism org : organisms) {
            if (organismsCount.containsKey(org.getClass())) {
                organismsCount.put(org.getClass(), organismsCount.get(org.getClass()) + 1);
            } else {
                organismsCount.put(org.getClass(), 1);
            }
        }

        Class<?> maxAnimal = organismsCount.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        return useSymbols ? SymbolMap.getSymbol(maxAnimal) : SymbolMap.getAbbrev(maxAnimal);
    }
}
