package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface MealService {

    void delete(int id, int userID) throws NotFoundException;

    Meal get(int id, int userID) throws NotFoundException;

    Meal save(Meal meal, int userID);

    void update(Meal meal, int userID);

    List<Meal> getAll(int userID);

    List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}