package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserUtil {

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static final List<User> users = Arrays.asList(
        new User(counter.incrementAndGet(),"Artur",  "colan80995@onzmail.com","123", Role.USER),
        new User(counter.incrementAndGet(),"Bob",  "colan80995@onzmail.com","123", Role.USER),
        new User(counter.incrementAndGet(),"Lena",  "colan80995@onzmail.com","123", Role.USER),
        new User(counter.incrementAndGet(),"Lily",  "colan80995@onzmail.com","123", Role.USER),
        new User(counter.incrementAndGet(),"Carter",  "colan80995@onzmail.com","123", Role.USER)
    );
}