package ru.restaurant_voting;

import ru.restaurant_voting.model.User;
import ru.restaurant_voting.util.UserUtil;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 1L;

    private User user;

    public AuthorizedUser(User user) {
        super(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, user.getRoles());
        this.user = user;
    }


    public int getId() {
        return user.id();
    }

    public void update(User newTo) {
        user = newTo;
    }

    public User getUserTo() {
        return user;
    }

    @Override
    public String toString() {
        return user.toString();
    }
}