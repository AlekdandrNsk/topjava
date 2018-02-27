package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.sql.rowset.Predicate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<UserMealWithExceed> b = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        for (UserMealWithExceed u : b) {
            System.out.println(u.toString());
        }
        System.out.println("++++++++++++++++++++++++++++++");
        List<UserMealWithExceed> b1 = getFilteredWithExceeded1(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        for (UserMealWithExceed u : b1) {
            System.out.println(u.toString());
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> sumForDay = new HashMap<>();

        List<UserMealWithExceed> result = new ArrayList<>();

        mealList.forEach(meal -> sumForDay.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (a, b) -> a + b));

        for (UserMeal meal : mealList) {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime))
                result.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        (sumForDay.get(meal.getDateTime().toLocalDate()) > caloriesPerDay)));
        }
        return result;
    }

     public static List<UserMealWithExceed> getFilteredWithExceeded1(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> sumForDay = mealList.stream().collect(Collectors.toMap(u -> u.getDateTime().toLocalDate(), UserMeal::getCalories, (a, b) -> a + b));

        return mealList.stream().filter(u -> TimeUtil.isBetween(u.getDateTime().toLocalTime(), startTime, endTime))
                                .map(u -> new UserMealWithExceed(u.getDateTime(), u.getDescription(), u.getCalories(),
                                    (sumForDay.get(u.getDateTime().toLocalDate()) > caloriesPerDay))).collect(Collectors.toList());

    }
}
