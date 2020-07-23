package ru.restaurant_voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant_voting.model.Report;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudReportRepository extends JpaRepository<Report, Integer> {

    @Transactional
    int deleteReportsByUserLink(int userId);

    List<Report> getAllByUserLink(int userId);

    Optional<Report> getFirstByRestaurantLink(int restaurantId);

    List<Report> getAllByRestaurantLink(int restaurantId);

    @Transactional
    int deleteReportsById(int id);

    @Transactional
    int deleteReportByRestaurantLink(int restaurantId);

//    List<Report> getReportByUserIdAndRestaurantIdAndDateTime_Day(int authUserId, int restaurantId, int day);

//    List<Report> getReportByUserIdAndRestaurantIdAndDateTime_DayAndDateTime(Integer userId, Integer restaurantId, int day, Date dateTime);
}