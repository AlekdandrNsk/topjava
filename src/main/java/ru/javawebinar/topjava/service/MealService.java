package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {

    void delete(int id, int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    Meal save(Meal meal, int userId);

    void update(Meal meal, int userId);

    List<Meal> getAll(int userId);

    List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}