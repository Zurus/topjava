package ru.javawebinar.topjava.web.meal;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

/**
 * JspMealController.
 *
 * @author ADivaev
 */

@Controller
public class JspMealController extends MealRestController {


    public JspMealController(MealService service) {
        super(service);
    }

    @PostMapping("/meals")
    public String doPost(HttpServletRequest request) {
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (StringUtils.hasLength(request.getParameter("id"))) {
            update(meal, getId(request));
        } else {
            create(meal);
        }

        return "redirect:meals";
    }


    @GetMapping("/meals/{action}")
    public String doGet(@PathVariable(value = "action") String action,
                        Model model, HttpServletRequest request) {

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(request);
                delete(id);
                return "redirect:meals";
            }
            case "create", "update" -> {
                final Meal meal = "create".equals(action) ?
                        new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        get(getId(request));
                //request.setAttribute("meal", meal);
                //request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                model.addAttribute("meal", meal);
                return "mealForm";
            }
            case "filter" -> {
                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(request.getParameter("endTime"));

                model.addAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
                //request.getRequestDispatcher("/meals.jsp").forward(request, response);
                return "meals";
            }
            default -> {
                model.addAttribute("meals", getAll());
                return "meals";
            }
        }
    }


    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

}