package com.javarush.island.vasileva.entity;

import com.javarush.island.vasileva.api.interfaces.Eating;
import com.javarush.island.vasileva.api.interfaces.Movable;
import com.javarush.island.vasileva.api.interfaces.Reproducible;
import com.javarush.island.vasileva.config.EatingChances;
import com.javarush.island.vasileva.entity.animals.Predator;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import com.javarush.island.vasileva.repository.OrganismFactory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import static com.javarush.island.vasileva.config.Setting.*;
import static com.javarush.island.vasileva.util.RandomValues.getRandomInt;
import static com.javarush.island.vasileva.util.RandomValues.getRandomNumber;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
public abstract class Organism implements Eating, Reproducible, Movable {
    private static final AtomicLong ID_COUNTER = new AtomicLong(1);

    private final long id;
    private Location location;
    private transient OrganismData organismData;
    private boolean isALive = true;
    protected int age = 0;
    private double weight;
    protected volatile boolean hasReproduced = false;

    public Organism() {
        this.id = ID_COUNTER.getAndIncrement();
        this.organismData = this.getClass().getAnnotation(OrganismData.class);
        if (organismData == null) {
            throw new RuntimeException("Class " + this.getClass().getSimpleName() + " must have @OrganismData annotation");
        }
    }

    public String getName() {
        return organismData.name();
    }

    public double getMaxWeight() {
        return organismData.maxWeight();
    }

    public int getSpeed() {
        return organismData.speed();
    }

    public double getFoodRequired() {
        return organismData.foodRequired();
    }

    public int getMaxPerCell() {
        return organismData.maxPerCell();
    }

    public boolean isEatable() {
        return organismData.eatable();
    }

    public String getImage() {
        return organismData.image();
    }

    public void placeOrganisms(Island island) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        int counter = ORGANISM_PLACEMENT_CYCLES;

        while (counter > 0) {
            int count = getRandomNumber(1, getMaxPerCell() + 1);
            int startX = getRandomInt(island.getHeight());
            int startY = getRandomInt(island.getWidth());
            for (int i = 0; i < count; i++) {
                Location location = island.getLocation(startX, startY);
                if (location != null) {
                    OrganismFactory.createOrganism(getClass(), location, getMaxWeight(), MIN_WEIGHT_FACTOR);
                }
            }
            counter--;
        }
    }

    protected List<Organism> findFood() {
        List<Organism> food = new ArrayList<>();
        for (Organism organism : location.getOrganisms()) {
            if (organism.isALive() && organism.isEatable() && !organism.getName().equals(getName())) {
                food.add(organism);
            }
        }
        return food;
    }

    protected boolean canEat(Organism item) {
        double chance = EatingChances.getChances(this.getClass(), item.getClass());
        return chance > 0 && ThreadLocalRandom.current().nextDouble() < chance;
    }

    protected void looseWeight() {
        setWeight(getWeight() * LOSE_WEIGHT_FACTOR);
        if (getWeight() < getMaxWeight() * DIE_WEIGHT_FACTOR) {
            die();
            location.removeOrganism(this);
        }
    }

    protected boolean isFoodAvailable(Organism food) {
        return food.isALive();
    }

    protected void consumeFood(Organism food) {
        if (food instanceof Organism prey) {
            prey.die();
        }
    }

    protected void setWeightsAfterEating(Organism food) {
        final double availableFoodWeight = food.getWeight();
        double consumedFood = Math.min(availableFoodWeight, getFoodRequired());
        double tentativeNewWeight = getWeight() + consumedFood;

        if (tentativeNewWeight <= getMaxWeight()) {
            setWeight(tentativeNewWeight);
            food.setWeight(availableFoodWeight - consumedFood);
        } else {
            double excessWeight = tentativeNewWeight - getMaxWeight();
            double actualConsumed = consumedFood - excessWeight;
            setWeight(getMaxWeight());
            food.setWeight(availableFoodWeight - actualConsumed);
        }
    }

    protected void performMove(Location firstLock, Location secondLock, Location from, Location to) {
        synchronized (firstLock) {
            synchronized (secondLock) {
                from.removeOrganism(this);
                to.addOrganism(this);
                this.setLocation(to);
            }
        }
    }

    protected boolean cannotReproduce() {
        if (this instanceof Predator) {
            return age < MIN_AGE_FOR_REPRODUCTION || hasReproduced || !isALive();
        } else {
            return age < MIN_AGE_FOR_REPRODUCTION || age % REPRODUCTION_FREQUENCY != 0 || hasReproduced || !isALive();
        }
    }

    protected List<Organism> findPotentialPartners() {
        return location.getOrganisms().stream()
                .filter(org ->
                        org.getClass() == this.getClass() &&
                                org.isALive()
                )
                .toList();
    }

    protected boolean hasSufficientPartners(List<Organism> partners) {
        int maxCapacity = getMaxPerCell();

        return partners.size() >= MIN_SPECIMENS_REQUIRED &&
                partners.size() < maxCapacity;
    }

    protected void tryReproduce() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        OrganismFactory.createOrganism(this.getClass(), location, this.getMaxWeight(), MIN_WEIGHT_FACTOR);
        hasReproduced = true;
    }

    public void die() {
        isALive = false;
        location.removeOrganism(this);
    }
}
