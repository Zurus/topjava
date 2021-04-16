package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;


@Repository
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(em.find(User.class, userId));
        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else {
            return em.merge(meal);
        }
    }

    @Override
    public boolean delete(int id, int userId) {

        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return em.find(Meal.class, id);
    }

    @Override
    public List<Meal> getAll(int userId) {

        return em.createNamedQuery(Meal.GET_ALL)
                .setParameter("user_id", userId)
                .getResultList();

    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAll(userId)
                .stream()
                .filter(m -> Util.isBetweenHalfOpen(m.getDateTime(),startDateTime, endDateTime))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}