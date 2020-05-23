package ru.restaurant_voting.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.restaurant_voting.RestaurantTestData;
import ru.restaurant_voting.UserTestData;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.RestaurantRepository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryRestaurantRepository implements RestaurantRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryRestaurantRepository.class);

    // Map  userId -> restaurantRepository
    private Map<Integer, InMemoryBaseRepository<Restaurant>> usersRestaurantsMap = new ConcurrentHashMap<>();

    {
        var userRestaurants = new InMemoryBaseRepository<Restaurant>();
        RestaurantTestData.RESTAURANTS.forEach(restaurant -> userRestaurants.map.put(restaurant.getId(), restaurant));
        usersRestaurantsMap.put(UserTestData.USER_ID, userRestaurants);
    }


    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        Objects.requireNonNull(restaurant, "restaurant must not be null");
        var restaurants = usersRestaurantsMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepository<>());
        return restaurants.save(restaurant);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        var restaurants = usersRestaurantsMap.get(userId);
        return restaurants != null && restaurants.delete(id);
    }

    @Override
    public Restaurant get(int id, int userId) {
        var restaurants = usersRestaurantsMap.get(userId);
        return restaurants == null ? null : restaurants.get(id);
    }

    @Override
    public Restaurant get(int id) {
        return null;
    }

    @Override
    public Restaurant getWithUser(int id, int userId) {
        return null;
    }

    @Override
    public List<Restaurant> getAll(int userId) {
        return getAllFiltered(userId, restaurant -> true);
    }

    private List<Restaurant> getAllFiltered(int userId, Predicate<Restaurant> filter) {
        var restaurants = usersRestaurantsMap.get(userId);
        return restaurants == null ? Collections.emptyList() :
                restaurants.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Restaurant::getName).reversed())
                        .collect(Collectors.toList());
    }
}