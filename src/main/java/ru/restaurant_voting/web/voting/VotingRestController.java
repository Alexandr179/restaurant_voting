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
import ru.restaurant_voting.model.Report;
import ru.restaurant_voting.repository.ReportRepository;
import ru.restaurant_voting.util.DateTimeUtil;
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
    private final ReportRepository reportRepository;

    public VotingRestController(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

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
    public boolean deleteUserReport() {
        int userId = SecurityUtil.authUserId();
        return reportRepository.deleteByUserId(userId);
    }

    @PutMapping(value = "/{id}/restaurants/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateReport(@RequestBody Report report, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(report, id);
        log.info("update voting {} for restaurant {}", id, restaurantId);
        report.setRestaurantLink(restaurantId);
        report.setUserLink(SecurityUtil.authUserId());
        Assert.notNull(report, "restaurant must not be null");
        checkNotFoundWithId(reportRepository.save(report), report.id());
    }

    @PostMapping(value = "/restaurants/{restaurantId}")// without requestBody -> //, consumes = MediaType.APPLICATION_JSON_VALUE
    public ResponseEntity<Report> createReportWithLocation(@PathVariable int restaurantId) {
        log.info("create voting report for restaurant {}", restaurantId);
        int authUserId = SecurityUtil.authUserId();
        Report report = new Report();

        if(DateTimeUtil.isVotingTodayReportBy(restaurantId)){// repeat-voting today
            Report created = reportRepository.getByRestaurantId(restaurantId).get();

            if(DateTimeUtil.isVotingBeforeControlTimeBy(restaurantId)){// voting is before CONTROL_TIME (11.00)
                reportRepository.deleteByRestaurantId(restaurantId);;// user rethinks.. and drop his voting-report
            }
            return new ResponseEntity<>(HttpStatus.OK);// no changes-status
        } else {//                                        new voting, isn't voting today
            report.setRestaurantLink(restaurantId);
            report.setUserLink(authUserId);
            Report created = reportRepository.save(report);

            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();
            return ResponseEntity.created(uriOfNewResource).body(created);
        }
    }
}