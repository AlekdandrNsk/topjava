package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<Meal> getAll() {
        log.info("getAll for User {}", AuthorizedUser.getID());
        return service.getAll(AuthorizedUser.getID());
    }

    public void delete(int id, int userID) {
        log.info("delete {} for User {}", id, AuthorizedUser.getID());
        service.delete(id, userID);
    }

    public Meal get(int id, int userID) throws NotFoundException {
        log.info("get {}", id);
        return service.get(id, userID);
    }


    public Meal create(Meal meal, int userID) {
        log.info("create {} for User {}", meal, AuthorizedUser.getID());
        checkNew(meal);
        return service.save(meal, userID);
    }

    public void update(Meal meal, int id, int userID) {
        log.info("update {} with id={} for User {}", meal, id, AuthorizedUser.getID());
        assureIdConsistent(meal, id);
        service.update(meal, userID);
    }

    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.getID();
        log.info("getBetween dates({} - {}) time({} - {}) for User {}", startDate, endDate, startTime, endTime, userId);
        return MealsUtil.getFilteredWithExceeded(
                service.getBetween(startDate != null ?
                                LocalDateTime.of(startDate, LocalTime.MIN) : LocalDateTime.of(LocalDate.MIN, LocalTime.MIN),
                        endDate != null ? LocalDateTime.of(endDate, LocalTime.MAX) : LocalDateTime.of(LocalDate.MAX, LocalTime.MAX), userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay());
    }
}