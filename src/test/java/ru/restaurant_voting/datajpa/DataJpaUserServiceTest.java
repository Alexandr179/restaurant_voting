package ru.restaurant_voting.datajpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.restaurant_voting.model.User;
import ru.restaurant_voting.service.AbstractUserServiceTest;
import ru.restaurant_voting.util.exception.NotFoundException;

import static ru.restaurant_voting.UserTestData.*;

class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    void get() throws Exception {
        User user = service.get(USER_ID);
        USER_MATCHER.assertMatch(user, USER);
    }

    @Test
    void getNotFound() throws Exception {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.get(100));
    }
}