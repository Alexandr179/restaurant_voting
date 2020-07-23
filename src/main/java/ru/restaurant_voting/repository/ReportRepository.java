package ru.restaurant_voting.repository;

import ru.restaurant_voting.model.Report;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
    // null if updated Report
    Report save(Report report);

    boolean deleteByUserId(int userId);

    List<Report> getAllByUserId(int userId);

    Optional<Report> getByRestaurantId(int restaurantId);

    boolean deleteById(int id);

//    List<Report> getReportTodayBy(int authUserId, int restaurantId, int today);

    Report getById(Integer integer);

    List<Report> getAllByRestaurantId(int restaurantId);

    boolean deleteByRestaurantId(int restaurantId);
}
