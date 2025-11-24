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
            System.out.println("+" + "=".repeat(SHOW_WIDTH * CELL_WIDTH + SHOW_WIDTH + 1));

            for (int x = 0; x < SHOW_HEIGHT; x++) {
                System.out.print("|");
                for (int y = 0; y < SHOW_WIDTH; y++) {
                    Location location = island.getLocation(x, y);
                    String cellContent = renderCell(location);
                    System.out.print(cellContent);
                    System.out.print("|");
                }
                System.out.println();
                System.out.println("+" + "=".repeat(SHOW_WIDTH * CELL_WIDTH + SHOW_WIDTH + 1));
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

        StringBuilder cell = new StringBuilder();
        for (int rank = 1; rank <= NUMBER_OF_SYMBOLS_IN_CELL; rank++) {
            String symbol = getNthOrganismSymbol(livingOrganisms, rank);
            cell.append(symbol.isEmpty() ? ' ' : symbol);
        }

        while (cell.length() < CELL_WIDTH) {
            cell.append(' ');
        }

        return cell.toString();
    }

    private String getNthOrganismSymbol(List<Organism> organisms, int n) {
        Map<Class<?>, Integer> count = countOrganismsByClass(organisms);
        Class<?> clazz = getNthMostFrequentClass(count, n);
        return formatClassRepresentation(clazz);
    }

    private Map<Class<?>, Integer> countOrganismsByClass(List<Organism> organisms) {
        Map<Class<?>, Integer> count = new HashMap<>();
        for (Organism org : organisms) {
            count.merge(org.getClass(), 1, Integer::sum);
        }
        return count;
    }

    private Class<?> getNthMostFrequentClass(Map<Class<?>, Integer> count, int n) {
        return count.entrySet()
                .stream()
                .sorted(Map.Entry.<Class<?>, Integer>comparingByValue().reversed())
                .skip(n - 1)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    private String formatClassRepresentation(Class<?> clazz) {
        if (clazz == null) {
            return " ";
        }
        return useSymbols ? SymbolMap.getSymbol(clazz) : SymbolMap.getAbbrev(clazz);
    }
}
