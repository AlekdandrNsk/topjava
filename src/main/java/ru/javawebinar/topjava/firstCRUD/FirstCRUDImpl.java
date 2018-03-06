package ru.javawebinar.topjava.firstCRUD;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstCRUDImpl implements FirstCRUD {
    private Map<Integer, Meal> mealDB = new ConcurrentHashMap<>();
    private static AtomicInteger mealId = new AtomicInteger(1);

    public Map<Integer, Meal> getMealDB() {
        List<Meal> meals = MealsUtil.createList();
        for (Meal meal : meals) {
            createMeal(meal);
        }
        return mealDB;
    }

    @Override
    public void createMeal(Meal meal) {
        meal.setId(mealId.getAndIncrement());
        mealDB.put(meal.getId(), meal);
    }

    @Override
    public Meal getMeal(int id) {
        return mealDB.get(id);
    }

    @Override
    public void deleteMeal(int id) {
        mealDB.remove(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealDB.values());
    }


    @Override
    public void updateMeal(Meal meal) {
        mealDB.put(meal.getId(), meal);
    }
}
