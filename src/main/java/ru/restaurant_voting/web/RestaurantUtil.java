package ru.restaurant_voting.web;

import ru.restaurant_voting.model.AbstractBaseEntity;

public class RestaurantUtil {

    private static int id = AbstractBaseEntity.START_SEQ + 2;


    public static int getRestaurantId() {
        return id;
    }

    public static void setRestaurantId(int id) {
        RestaurantUtil.id = id;
    }
}
