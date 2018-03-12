package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public void delete(int id, int userID) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userID), id);
    }

    @Override
    public Meal get(int id, int userID) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userID), id);
    }

    @Override
    public Meal save(Meal meal, int userID) {
        return checkNotFoundWithId(repository.save(meal, userID), meal.getId());
    }

    @Override
    public void update(Meal meal, int userID) {
        checkNotFoundWithId(repository.save(meal, userID), meal.getId());
    }

    @Override
    public List<Meal> getAll(int userID) {
        return repository.getAll(userID);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return repository.getBetween(userId, startDateTime, endDateTime);
    }

}