package ru.restaurant_voting.repository;

import ru.restaurant_voting.model.Menu;
import java.util.List;

public interface MenuRepository {
    // null if updated Restaurant do not belong to userId
    Menu save(Menu menu, int restaurantId);

    // false if Restaurant do not belong to userId
    boolean delete(int i, int id);

    // null if Restaurant do not belong to userId
    Menu get(int id, int restaurantId);

    Menu get(int id);

    default Menu getWithRestaurant(int id, int restaurantId) {
        throw new UnsupportedOperationException();
    }

    List<Menu> getAll(int restaurantId);
}
