package ru.restaurant_voting.web.voting;

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
import ru.restaurant_voting.repository.MenuRepository;

import java.net.URI;

import static ru.restaurant_voting.util.ValidationUtil.assureIdConsistent;
import static ru.restaurant_voting.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = VotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingRestController {
    static final String REST_URL = "/rest/admin/users/voting";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuRepository menuRepository;

//    @GetMapping("/{restaurantId}")
//    public List<Menu> getAll(@PathVariable int restaurantId) {
//        log.info("getAll voting for restaurantId {}", restaurantId);
//        return menuRepository.getAll(restaurantId);
//    }

//    @GetMapping("/{id}/{restaurantId}")
//    public Menu get(int id, int restaurantId) {
//        log.info("get voting {} for restaurant {}", id, restaurantId);
//        return menuRepository.get(id, restaurantId);
//    }
//
//    @DeleteMapping("/{id}/{restaurantId}")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
//        menuRepository.delete(id, restaurantId);
//    }
//
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void update(@RequestBody Menu voting, @PathVariable int id) {// !! Menu подаем с id=null
//        assureIdConsistent(voting, id);   // устанавливаем id для полученного (из тела JSON) voting
//        log.info("update voting {} from {}", voting, id);
//        Assert.notNull(voting, "voting must not be null");
//                                        // устанавливаем Restaurant для полученного (из тела JSON) voting по известному  voting.id
//        Restaurant restaurant = menuRepository.get(id).getRestaurant();
//        voting.setRestaurant(restaurant);
//                                        // передаем в метод save репозитория обновленную сущность, не более...
////        checkNotFoundWithId(menuRepository.save(voting, restaurant.getId()), voting.id());
//        menuRepository.save(voting, restaurant.getId());
//    }
//
//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void update(@RequestBody Menu menu, @PathVariable int id) {
//        int restaurantId = menuRepository.get(id).getRestaurant().getId();
//        assureIdConsistent(menu, id);
//        log.info("update {} for restaurant {}", menu, restaurantId);
//        Assert.notNull(menu, "restaurant must not be null");
//        checkNotFoundWithId(menuRepository.save(menu, restaurantId), menu.id());
//    }
//
//    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Menu> createWithLocation(@RequestBody Menu menu, @PathVariable int restaurantId) {
//        log.info("create {} for restaurant {}", menu, restaurantId);
//        Menu created = menuRepository.save(menu, restaurantId);
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path(REST_URL + "/{id}")
//                .buildAndExpand(created.getId()).toUri();
//        return ResponseEntity.created(uriOfNewResource).body(created);
//    }
}