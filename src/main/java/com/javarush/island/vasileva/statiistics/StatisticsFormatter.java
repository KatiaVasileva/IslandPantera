package com.javarush.island.vasileva.statiistics;

import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.entity.Organism;
import com.javarush.island.vasileva.view.SymbolMap;

import java.lang.annotation.Annotation;
import java.util.Map;

public class StatisticsFormatter {
    public String format(Map<Class<? extends Organism>, Integer> organismCounts,
                         int tickCounter) {

        StringBuilder sb = new StringBuilder();
        sb.append("\n--- ТАКТ ").append(tickCounter).append(" ---\n");
        sb.append("Всего животных: ").append(sumValues(organismCounts)).append("\n\n");

        sb.append("Животные:\n");
        for (Map.Entry<Class<? extends Organism>, Integer> entry : organismCounts.entrySet()) {
            sb.append(formatOrganismEntry(entry.getKey(), entry.getValue())).append("\n");
        }

        return sb.toString();
    }

    private String formatOrganismEntry(Class<?> clazz, int count) {
        String symbol = SymbolMap.getSymbol(clazz);
        String name = getOrganismName(clazz);
        return String.format("%s (%s): %d", symbol, name, count);
    }

    private int sumValues(Map<?, Integer> map) {
        return map.values().stream().mapToInt(Integer::intValue).sum();
    }

    private String getOrganismName(Class<?> clazz) {
        Annotation annotation = clazz.getAnnotation(OrganismData.class);
        if (annotation instanceof OrganismData data) {
            return data.name();
        }
        return clazz.getSimpleName();
    }
}
