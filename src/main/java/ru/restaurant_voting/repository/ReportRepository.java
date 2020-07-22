package ru.restaurant_voting.repository;

import ru.restaurant_voting.model.Report;

import java.util.List;

public interface ReportRepository {
    // null if updated Report
    Report save(Report report);

    boolean delete(int id);

    List<Report> getAllByUserId(int userId);

    List<Report> getAllByRestaurantId(int restaurantId);
}
