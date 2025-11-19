package com.javarush.island.vasileva.view;

import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.entity.Organism;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static com.javarush.island.vasileva.config.Setting.*;

@Getter
@Setter
public class ConsoleRenderer {
    private final Island island;
    private boolean useSymbols = true;
    private final ReentrantLock renderLock = new ReentrantLock();

    public ConsoleRenderer(Island island) {
        this.island = island;
    }

    public void render() {
        renderLock.lock();
        try {
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
        } finally {
            renderLock.unlock();
        }
    }

    private String renderCell(Location loc) {
        List<Organism> livingOrganisms = new ArrayList<>();

        for (Organism organism : loc.getOrganisms()) {
            if (organism.isALive()) {
                livingOrganisms.add(organism);
            }
        }

        if (livingOrganisms.isEmpty()) {
            return " ".repeat(CELL_WIDTH);
        }

        String symbol = getMaxOrganismForCellRendering(livingOrganisms);

        if (symbol.length() > CELL_WIDTH) {
            symbol = symbol.substring(0, CELL_WIDTH);
        }
        return String.format("%-" + CELL_WIDTH + "s", symbol);
    }

    // Show organism with the maximum number of animals/plants in the location
    private String getMaxOrganismForCellRendering(List<Organism> organisms) {
        Map<Class<?>, Integer> organismsCount = new HashMap<>();

        for (Organism org : organisms) {
            organismsCount.merge(org.getClass(), 1, Integer::sum);
        }

        Class<?> maxClass = organismsCount.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        return useSymbols
                ? (maxClass != null ? SymbolMap.getSymbol(maxClass) : " ")
                : (maxClass != null ? SymbolMap.getAbbrev(maxClass) : " ");
    }
}
