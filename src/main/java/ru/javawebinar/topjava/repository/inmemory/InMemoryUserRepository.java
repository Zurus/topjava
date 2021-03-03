package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private final static List<User> users = new ArrayList<>();

    {
        UserUtil.users.forEach(this::save);
    }


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return true;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        users.add(user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return users.stream().filter(u->u.getId()==id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");

        users.sort(Comparator.comparing(User::getName));

        return users;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return users.stream().filter(u->u.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }


//    public static void main(String[] args) {
//        InMemoryUserRepository repo = new InMemoryUserRepository();
//        System.out.println("user [0] = " + repo.get(1));
//        System.out.println("uer [228] = " + repo.get(228));
//    }

}
