package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.firstCRUD.SimpleCRUDImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;


import static org.slf4j.LoggerFactory.getLogger;

@WebServlet(urlPatterns = "/meals", name = "MealServlet")
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private SimpleCRUDImpl simpleCRUDimpl = new SimpleCRUDImpl();

    @Override
    public void init() throws ServletException {
        simpleCRUDimpl.getMealDB();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String idString = request.getParameter("id_edit");
        int id = 0;
        if (!idString.equals("")){
        id = (Integer.parseInt(idString));}
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        LocalDateTime dlt = LocalDateTime.parse(request.getParameter("datetime"), TimeUtil.formatter);

        if (id == 0){
            simpleCRUDimpl.createMeal(new Meal(dlt, description, calories));
        }else {
            Meal meal = simpleCRUDimpl.getMeal(id);
            simpleCRUDimpl.updateMeal(new Meal(meal.getId(), dlt, description, calories));
        }

        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if(action != null){
            if (action.equals("delete")){
                simpleCRUDimpl.deleteMeal(Integer.parseInt(id));
                response.sendRedirect("meals");
            }else if (action.equals("edit")){
                Meal meal = simpleCRUDimpl.getMeal(Integer.parseInt(id));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("meal.jsp").forward(request, response);
            }
        }else {request.setAttribute("meals", MealsUtil.getFilteredWithExceeded(simpleCRUDimpl.getAllMeals(),
                LocalTime.MIN, LocalTime.MAX, 2000));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);}



    }
}
























