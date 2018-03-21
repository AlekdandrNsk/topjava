package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_1_ID = START_SEQ + 2;

    public static final Meal MEAL_1 = new Meal(MEAL_1_ID, LocalDateTime.of(2015, Month.MAY, 30, 8, 0), "Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(MEAL_1_ID + 1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_3 = new Meal(MEAL_1_ID + 2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_4 = new Meal(MEAL_1_ID + 3, LocalDateTime.of(2015, Month.MAY, 31, 8, 0), "Завтрак", 1000);
    public static final Meal MEAL_5 = new Meal(MEAL_1_ID + 4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL_6 = new Meal(MEAL_1_ID + 5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static final List<Meal> MEALS = Arrays.asList(MEAL_3, MEAL_2, MEAL_1);

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }

}
