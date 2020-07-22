package ru.restaurant_voting.web.voting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.restaurant_voting.model.Menu;
import ru.restaurant_voting.model.Report;
import ru.restaurant_voting.model.Restaurant;
import ru.restaurant_voting.repository.ReportRepository;
import ru.restaurant_voting.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.restaurant_voting.util.ValidationUtil.*;

@RestController
@RequestMapping(value = VotingRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingRestController {
    static final String REST_URL = "/rest/admin/users/voting";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReportRepository reportRepository;

    @GetMapping
    public List<Report> getReportRestaurantsByAuthUser() {
        int userId = SecurityUtil.authUserId();
        log.info("get voting report for authUser {}", userId);
        return reportRepository.getAllByUserId(userId);
    }

    @GetMapping("/{restaurantId}")
    public List<Report> getReportUsersByRestaurant(@PathVariable int restaurantId) {
        log.info("get voting report for restaurant {}", restaurantId);
        return reportRepository.getAllByRestaurantId(restaurantId);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        int userId = SecurityUtil.authUserId();
        reportRepository.delete(userId);
    }

//    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(value = HttpStatus.NO_CONTENT)
//    public void update(@RequestBody Menu menu, @PathVariable int id) {
//        int restaurantId = menuRepository.get(id).getRestaurant().getId();
//        assureIdConsistent(menu, id);
//        log.info("update {} for restaurant {}", menu, restaurantId);
//        Assert.notNull(menu, "restaurant must not be null");
//        checkNotFoundWithId(menuRepository.save(menu, restaurantId), menu.id());
//    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Report> createWithLocation(@PathVariable int restaurantId) {
        log.info("create voting report for restaurant {}", restaurantId);
        Report report = new Report();
        report.setRestaurantId(restaurantId);
        report.setUserId(SecurityUtil.authUserId());
        Report created = reportRepository.save(report);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}