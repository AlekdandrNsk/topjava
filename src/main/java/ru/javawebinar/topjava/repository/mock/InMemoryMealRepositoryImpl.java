package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.DateTimeUtil.isBetween;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m -> save(m, m.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userID) {
        if (meal.getUserId() != userID)
            return null;
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userID) {
        Meal meal = repository.get(id);
        return meal.getUserId() == userID && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userID) {
        Meal meal = repository.get(id);
        if (meal.getUserId() != userID) return null;
        else return meal;
    }

    @Override
    public List<Meal> getAll(int userID) {
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userID)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(int userId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Objects.requireNonNull(startDateTime);
        Objects.requireNonNull(endDateTime);
        return getAllSorted(this.getAll(userId).stream().filter(meal -> isBetween(meal.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList()));
    }

    public List<Meal> getAllSorted(List<Meal> meals) {
        return meals.stream()
                .sorted((meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime())).collect(Collectors.toList());
    }
}

