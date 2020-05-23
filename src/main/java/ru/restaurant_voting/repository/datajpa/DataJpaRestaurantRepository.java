package ru.restaurant_voting.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.RestaurantRepository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }


    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant, int userId) {
        if (!restaurant.isNew() && get(restaurant.id(), userId) == null) {
            return null;
        }
        restaurant.setUser(crudUserRepository.getOne(userId));
        return crudRestaurantRepository.save(restaurant);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRestaurantRepository.delete(id, userId) != 0;
    }

    @Override
    public Restaurant get(int id, int userId) {
        return crudRestaurantRepository.findById(id).filter(Restaurant ->
                Restaurant.getUser().getId() == userId).orElse(null);
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public Restaurant getWithUser(int id, int userId) {
        return crudRestaurantRepository.getWithUser(id, userId);
    }

    @Override
    public List<Restaurant> getAll(int userId) {
        return crudRestaurantRepository.getAll(userId);
    }
}
