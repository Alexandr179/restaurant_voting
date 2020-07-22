package ru.restaurant_voting.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant_voting.model.Report;

import java.util.List;


@Transactional(readOnly = true)
public interface CrudReportRepository extends JpaRepository<Report, Integer> {

    @Transactional
    int deleteReportsByUserId(int userId);

    List<Report> getAllByUserId(int userId);

    List<Report> getAllByRestaurantId(int restaurantId);
}