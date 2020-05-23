package ru.restaurant_voting.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.MenuRepository;

import java.util.List;


@Repository
public class DataJpaMenuRepository implements MenuRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudMenuRepository crudMenuRepository;

    public DataJpaMenuRepository(CrudRestaurantRepository crudRestaurantRepository, CrudMenuRepository crudMenuRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMenuRepository = crudMenuRepository;
    }

    @Override
    @Transactional
    public Menu save(Menu menu, int restaurantId) {
        if (!menu.isNew() && get(menu.id(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMenuRepository.save(menu);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMenuRepository.delete(id, userId) != 0;
    }

    @Override
    public Menu get(int id, int restaurantId) {
        return crudMenuRepository.findById(id).filter(Menu ->
                Menu.getRestaurant().getId() == restaurantId).orElse(null);
    }

    @Override
    public Menu get(int id) {
        return crudMenuRepository.findById(id).orElse(null);
    }

    @Override
    public Menu getWithRestaurant(int id, int restaurantId) {
        return crudMenuRepository.getWithRestaurant(id, restaurantId);
    }

    @Override
    public List<Menu> getAll(int restaurantId) {
        return crudMenuRepository.getAll(restaurantId);
    }
}
