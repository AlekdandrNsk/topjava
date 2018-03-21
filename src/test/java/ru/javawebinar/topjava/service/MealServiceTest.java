package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))

public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1_ID, USER_ID);
        assertMatch(meal, MEAL_1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        service.get(1, USER_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL_1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEAL_3, MEAL_2);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundDelete() {
        service.delete(1, USER_ID);
    }

    @Test
    public void getBetweenDates() {
        assertMatch(service.getBetweenDates(LocalDate.of(2015, Month.MAY, 30), LocalDate.of(2015, Month.MAY, 30), USER_ID),
                MEAL_3, MEAL_2, MEAL_1);
    }

    @Test
    public void getBetweenDateTimes() {
        assertMatch(service.getBetweenDateTimes(LocalDateTime.of(2015, Month.MAY, 30, 7, 0),
                LocalDateTime.of(2015, Month.MAY, 30, 21, 0), USER_ID),
                MEAL_3, MEAL_2, MEAL_1);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL_1);
        updated.setCalories(330);
        updated.setDescription("UpdatedDescription");
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL_1_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void notFoundUpdate() {
        service.update(MEAL_1, ADMIN_ID);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(LocalDateTime.now(), "TestDescription", 1555);
        service.create(newMeal, USER_ID);
        assertMatch(service.getAll(USER_ID), newMeal, MEAL_3, MEAL_2, MEAL_1);
    }
}



























