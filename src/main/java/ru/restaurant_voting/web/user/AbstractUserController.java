package ru.restaurant_voting.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.repository.UserRepository;

import java.util.List;

import static ru.restaurant_voting.util.ValidationUtil.assureIdConsistent;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        log.info("getAll");
        return userRepository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return userRepository.get(id);
    }

    public User create(User user) {
        log.info("create {}", user);
        return userRepository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        userRepository.delete(id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        userRepository.save(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return userRepository.getByEmail(email);
    }
}