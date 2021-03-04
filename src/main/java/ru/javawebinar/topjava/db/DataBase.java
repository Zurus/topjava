package ru.javawebinar.topjava.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

public class DataBase {

    private static final Map<User,Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void save(User user, Meal meal) {
        if(meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(user, new ConcurrentHashMap<Integer, Meal>(){{
                put(meal.getId(), meal);
            }});
        }

        Map<Integer, Meal> map = repository.get(user);
        map.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }


    public static Meal getMeal(int id) {
        int userId = SecurityUtil.authUserId();
        User user = (User) repository.entrySet().stream().filter(u-> u.getKey().getId().intValue() == userId).findFirst().orElse(null);
        if (user == null) {
            throw new NotFoundException("Пользователь не найден!");
        }
        Meal meal = repository.get(user).get(id);
        if (meal == null) {
            throw new NotFoundException("Нет еды!");
        }
        return meal;
    }

    public static Collection<Meal> getAll() {
        User user = (User) repository.entrySet().stream().filter(u-> u.getKey().getId().intValue() == SecurityUtil.authUserId()).findFirst().orElse(null);
        return repository.get(user).values().stream().sorted(Comparator.comparing(Meal::getDateTime)).collect(Collectors.toCollection(ArrayList::new));
    }

}