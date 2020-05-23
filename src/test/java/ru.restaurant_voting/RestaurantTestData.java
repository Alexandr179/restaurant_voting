package ru.restaurant_voting;

import ru.restaurant_voting.model.Restaurant;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.restaurant_voting.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> MEAL_MATCHER = TestMatcher.usingFieldsComparator(Restaurant.class, "restaurant");

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int ADMIN_RESTAURANT_ID = START_SEQ + 9;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Теремок", "Москва...", "89110337001");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID, "Окна", "Санкт-Петербург...", "89110337001");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID, "Простой ресторан", "Казань...", "89110337001");

    public static final Restaurant ADMIN_RESTAURANT1 = new Restaurant(ADMIN_RESTAURANT_ID, "Admin: Теремок", "Москва...", "89110337001");
    public static final Restaurant ADMIN_RESTAURANT2 = new Restaurant(ADMIN_RESTAURANT_ID + 1,"Admin: Окна", "Санкт-Петербург...", "89110337001");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT3, RESTAURANT2, RESTAURANT1);

    public static Restaurant getNew() {
        return new Restaurant(null, "Ресторан до обновления", "Москва...", "89110337001");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Обновленный ресторан", "Москва...", "89110337001");
    }
}
