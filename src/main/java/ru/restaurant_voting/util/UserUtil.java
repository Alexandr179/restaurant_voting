package ru.restaurant_voting.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;

public class UserUtil {

    //  passwordEncoding..

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    public static User update(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}