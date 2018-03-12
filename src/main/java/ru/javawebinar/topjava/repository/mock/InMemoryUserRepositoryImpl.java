package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();

//    {
//        repository.put(1, new User(1, "Vasya", "qwerty", "12345", Role.ROLE_USER));
//        repository.put(2, new User(2, "Petya", "qwerty1", "12345", Role.ROLE_USER));
//        repository.put(3, new User(3, "Vasya", "afghh", "12345", Role.ROLE_USER));
//        repository.put(4, new User(4, "Kostya", "qwerty3", "12345", Role.ROLE_USER));
//    }
//
//    public static void main(String[] args) {
//        InMemoryUserRepositoryImpl i = new InMemoryUserRepositoryImpl();
//        List<User> userList = i.getAll();
//        userList.forEach(System.out::println);
//    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return repository.put(user.getId(), user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> users = new ArrayList<>(repository.values());
        Collections.sort(users);
        return users;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(user1 -> user1.getEmail().equals(email)).findFirst().orElse(null);
    }
}
