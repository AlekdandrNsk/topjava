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
        log.info("getAll for User {}", AuthorizedUser.getId());
        return service.getAll(AuthorizedUser.getId());
    }

    public void delete(int id) {
        log.info("delete {} for User {}", id, AuthorizedUser.getId());
        service.delete(id, AuthorizedUser.getId());
    }

    public Meal get(int id) throws NotFoundException {
        log.info("get {}", id);
        return service.get(id, AuthorizedUser.getId());
    }


    public Meal create(Meal meal) {
        log.info("create {} for User {}", meal, AuthorizedUser.getId());
        checkNew(meal);
        return service.save(meal, AuthorizedUser.getId());
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={} for User {}", meal, id, AuthorizedUser.getId());
        assureIdConsistent(meal, id);
        service.update(meal, AuthorizedUser.getId());
    }

    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.getId();
        log.info("getBetween dates({} - {}) time({} - {}) for User {}", startDate, endDate, startTime, endTime, userId);
        return MealsUtil.getFilteredWithExceeded(
                service.getBetween(startDate, endDate, userId),
                startTime != null ? startTime : LocalTime.MIN,
                endTime != null ? endTime : LocalTime.MAX,
                AuthorizedUser.getCaloriesPerDay());
    }
}