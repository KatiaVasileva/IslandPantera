package com.javarush.island.vasileva.view;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConsoleRenderer {
    private final Island island;
    private boolean useSymbols = true;
    private boolean useColors = true;


    public ConsoleRenderer(Island island) {
        this.island = island;
    }

    public void render() {
        int width = island.getWidth();
        int height = island.getHeight();

        System.out.println("\n!" + "-".repeat(width * 4 + 1));

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                Location location = island.getLocation(x, y);
                String cellContent = renderCell(location);
                System.out.printf("|%s", cellContent);
            }
            System.out.println("|");
            System.out.println("-".repeat(width * 4 + 1));
        }
    }

    private String renderCell(Location loc) {
        List<Animal> animals = loc.getAnimals();
        List<Plant> plants = loc.getPlants();

        if (animals.isEmpty() && plants.isEmpty()) {
            return "   ";
        }

        String symbol;
        if (!animals.isEmpty()) {
            Animal animal = animals.getFirst();
            symbol = useSymbols ? SymbolMap.getSymbol(animal) : SymbolMap.getAbbrev(animal);
        } else {
            symbol = useSymbols ? SymbolMap.getSymbol(plants.getFirst()) : SymbolMap.getAbbrev(plants.getFirst());
        }

        String coloredSymbol = symbol;
        if (useColors) {
            if (!animals.isEmpty()) {
                coloredSymbol = ColorScheme.ANIMAL + symbol + ColorScheme.RESET;
            } else {
                coloredSymbol = ColorScheme.PLANT + symbol + ColorScheme.RESET;
            }
        }

        // Доводим до 3 символов (с отступами)
        return String.format("%-10s", coloredSymbol);
    }

}
