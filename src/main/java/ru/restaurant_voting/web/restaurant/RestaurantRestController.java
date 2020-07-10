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
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.repository.RestaurantRepository;
import ru.restaurant_voting.repository.UserRepository;
import ru.restaurant_voting.util.DateTimeUtil;
import ru.restaurant_voting.web.SecurityUtil;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static ru.restaurant_voting.util.ValidationUtil.*;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    static final String REST_URL = "/rest/user/restaurants";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    DateTimeUtil dateTimeUtil;

    @GetMapping
    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return restaurantRepository.getAll(userId);
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
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
        Date newDate = new Date();
//        dateTimeUtil.checkVoting(newDate, id);
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
}