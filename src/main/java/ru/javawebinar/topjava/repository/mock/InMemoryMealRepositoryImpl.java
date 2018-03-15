package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
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
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return isMealNotNullAndTrueUserId(meal.getId(), userId) ? repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal) : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return isMealNotNullAndTrueUserId(id, userId) && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        return isMealNotNullAndTrueUserId(id, userId) ? repository.get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getBetween(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public List<Meal> getBetween(int userId, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate);
        Objects.requireNonNull(endDate);
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .filter(meal -> isBetween(meal.getDate(), startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    private boolean isMealNotNullAndTrueUserId(int id, int userId) {
        Meal meal = repository.get(id);
        return (meal != null && meal.getUserId() == userId);
    }
}

