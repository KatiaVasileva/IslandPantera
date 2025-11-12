package com.javarush.island.vasileva.view;

import com.javarush.island.vasileva.Island;
import com.javarush.island.vasileva.Location;
import com.javarush.island.vasileva.entity.animals.Animal;
import com.javarush.island.vasileva.entity.plants.Plant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
        List<Animal> animals = loc.getAnimals();
        List<Plant> plants = loc.getPlants();

        if (animals.isEmpty() && plants.isEmpty()) {
            return " ".repeat(CELL_WIDTH);
        }

        String symbol;
        if (!animals.isEmpty()) {
            Animal animal = animals.getFirst();
            symbol = useSymbols ? SymbolMap.getSymbol(animal) : SymbolMap.getAbbrev(animal);
        } else {
            symbol = useSymbols ? SymbolMap.getSymbol(plants.getFirst()) : SymbolMap.getAbbrev(plants.getFirst());
        }

        if (symbol.length() > CELL_WIDTH) {
            symbol = symbol.substring(0, CELL_WIDTH);
        }

        return String.format("%-" + CELL_WIDTH + "s", symbol);


    }

}
