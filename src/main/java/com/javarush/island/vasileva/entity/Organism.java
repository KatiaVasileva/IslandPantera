package com.javarush.island.vasileva.entity;

import com.javarush.island.vasileva.api.interfaces.Eating;
import com.javarush.island.vasileva.api.interfaces.Movable;
import com.javarush.island.vasileva.api.interfaces.Reproducible;
import com.javarush.island.vasileva.config.EatingChances;
import com.javarush.island.vasileva.entity.map.Island;
import com.javarush.island.vasileva.entity.map.Location;
import com.javarush.island.vasileva.api.annotations.OrganismData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

import static com.javarush.island.vasileva.config.Setting.*;

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
        OrganismData data = getData(this);
        int maxPerCell = data.maxPerCell();
        int counter = ORGANISM_PLACEMENT_CYCLES;

        while (counter > 0) {
            int count = ThreadLocalRandom.current().nextInt(1, maxPerCell + 1);
            int startX = ThreadLocalRandom.current().nextInt(island.getHeight());
            int startY = ThreadLocalRandom.current().nextInt(island.getWidth());
            for (int i = 0; i < count; i++) {
                Location location = island.getLocation(startX, startY);
                if (location != null) {
                    Organism organism = this.getClass().getConstructor().newInstance();
                    organism.setWeight(ThreadLocalRandom.current().nextDouble(data.maxWeight() - data.maxWeight() * 0.2, data.maxWeight()));
                    location.addOrganism(organism);
                    organism.setLocation(location);
                }
            }
            counter--;
        }
    }

    protected List<Organism> findFood() {
        List<Organism> food = new ArrayList<>();
        for (Organism organism : location.getOrganisms()) {
            if (organism.isALive() && organism.isEatable()) {
                food.add(organism);
            }
        }
        return food;
    }

    protected boolean canEat(Organism item) {
        double chance = EatingChances.getChances(this.getClass(), item.getClass());
//        return chance > 0 && Math.random() < chance;
        return chance > 0;
    }

    protected void looseWeight() {
        setWeight(getWeight() - getWeight() * 0.05);
        if (getWeight() < getMaxWeight() * 0.1) {
            die();
            location.removeOrganism(this);
//            System.out.println(getName() + getId() + " died");
        }
    }

    protected boolean isFoodAvailable(Organism food) {
        return food.isALive();
    }

    protected void consumeFood(Organism food) {
        if (food instanceof Organism prey) {
            prey.die();
            location.removeOrganism(prey);
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
        return age < 5 || hasReproduced || !isALive();
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
        int minPartnersRequired = 2;
        int maxCapacity = getData(this).maxPerCell();

        return partners.size() >= minPartnersRequired &&
                partners.size() < maxCapacity;
    }

    protected void tryReproduce() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Organism offspring = createOffspring();
        location.addOrganism(offspring);
        offspring.setLocation(location);
//        hasReproduced = true;
//        logReproduce(this, offspring, location);
    }

    protected Organism createOffspring() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Organism offspring = this.getClass().getDeclaredConstructor().newInstance();
        offspring.setWeight(ThreadLocalRandom.current().nextDouble(ThreadLocalRandom.current().nextDouble(getMaxWeight() - getMaxWeight() * 0.2, getMaxWeight())));
        return offspring;
    }

    public void die() {
        isALive = false;
    }
}
