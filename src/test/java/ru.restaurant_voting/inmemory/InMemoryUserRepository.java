package ru.restaurant_voting.inmemory;

import org.springframework.stereotype.Repository;
import ru.restaurant_voting.UserTestData;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.restaurant_voting.UserTestData.ADMIN;
import static ru.restaurant_voting.UserTestData.USER;


@Repository
public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {

    public void init() {
        map.clear();
        map.put(UserTestData.USER_ID, USER);
        map.put(UserTestData.ADMIN_ID, ADMIN);
    }

    @Override
    public List<User> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }


    @Override
    public void enable(int id, boolean enabled) {

    }

    @Override
    public User getByEmail(String email) {
        Objects.requireNonNull(email, "email must not be null");
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }
}