package ru.restaurant_voting.repository;

import ru.restaurant_voting.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if updated Restaurant do not belong to userId
    Restaurant save(Restaurant restaurant, int userId);

    // false if Restaurant do not belong to userId
    boolean delete(int id, int userId);

    // null if Restaurant do not belong to userId
    Restaurant get(int id, int userId);

    Restaurant get(int id);

    default Restaurant getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }

    List<Restaurant> getAll(int userId);

}
