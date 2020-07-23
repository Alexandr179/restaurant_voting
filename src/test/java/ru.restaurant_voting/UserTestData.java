package ru.restaurant_voting;

import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.web.json.JsonUtil;

import java.util.Collections;
import java.util.Date;

import static ru.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsComparator(User.class, "registered", "meals", "password");

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }

    public static final int ADMIN_ID = START_SEQ;
    public static final int USER_ID = START_SEQ + 1;
    public static final int USER_ID_2 = START_SEQ + 2;
    public static final int USER_ID_3 = START_SEQ + 3;


    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN, Role.USER);
    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User USER_2 = new User(USER_ID_2, "User2", "user2@yandex.ru", "password3", Role.USER);
    public static final User USER_3 = new User(USER_ID_3, "User3", "user3@yandex.ru", "password3", Role.USER);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", true, Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setName("UpdatedName");
//        updated.set.......................();
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
