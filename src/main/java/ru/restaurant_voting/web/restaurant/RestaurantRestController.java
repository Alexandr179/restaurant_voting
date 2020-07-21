package ru.restaurant_voting.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.MenuRepository;
import ru.restaurant_voting.repository.RestaurantRepository;

import ru.restaurant_voting.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.restaurant_voting.util.ValidationUtil.*;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/admin/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return restaurantRepository.getAll(userId);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        return restaurantRepository.get(id, userId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} for user {}", id, userId);
        restaurantRepository.delete(id, userId);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(restaurant, id);
        log.info("update {} for user {}", restaurant, userId);
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(restaurantRepository.save(restaurant, userId), restaurant.id());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        checkNew(restaurant);
        log.info("create {} for user {}", restaurant, userId);
        Restaurant created = restaurantRepository.save(restaurant, userId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    // Menu methods

    @GetMapping("/{restaurantId}/menus")
    public List<Menu> getAllRestaurantMenus(@PathVariable int restaurantId) {
        log.info("get menus restaurant {}", restaurantId);
        return menuRepository.getAll(restaurantId);
    }

    @GetMapping("/{restaurantId}/menus/{id}")
    public Menu getRestaurantsMenu(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("get voting {} for restaurant {}", id, restaurantId);
        return menuRepository.get(id, restaurantId);
    }

    @DeleteMapping("/{restaurantId}/menus/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete voting {} for restaurant {}", id, restaurantId);
        menuRepository.delete(id, restaurantId);
    }

    @PutMapping(value = "{restaurantId}/menus/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateMenu(@RequestBody Menu menu, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(menu, id);
        log.info("update voting {} for restaurant {}", id, restaurantId);
        Assert.notNull(menu, "restaurant must not be null");
        checkNotFoundWithId(menuRepository.save(menu, restaurantId), menu.id());
    }

    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createMenuWithLocation(@RequestBody Menu menu, @PathVariable int restaurantId) {
        checkNew(menu);
        log.info("create voting {} for restaurant {}", menu, restaurantId);
        Menu created = menuRepository.save(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}