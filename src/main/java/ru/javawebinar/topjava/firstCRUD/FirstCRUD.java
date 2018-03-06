package ru.javawebinar.topjava.firstCRUD;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Map;

public interface FirstCRUD {

    void createMeal(Meal meal);

    Meal getMeal(int id);

    void deleteMeal(int id);

    void updateMeal(Meal meal);

    Map<Integer, Meal> getMealDB();

    List<Meal> getAllMeals();
}
