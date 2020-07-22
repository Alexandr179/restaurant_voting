package ru.restaurant_voting.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant_voting.model.Report;
import ru.restaurant_voting.repository.ReportRepository;

import java.util.List;


@Repository
public class DataJpaReportRepository implements ReportRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudMenuRepository crudMenuRepository;
    private final CrudReportRepository crudReportRepository;


    public DataJpaReportRepository(CrudRestaurantRepository crudRestaurantRepository, CrudMenuRepository crudMenuRepository, CrudReportRepository crudReportRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudMenuRepository = crudMenuRepository;
        this.crudReportRepository = crudReportRepository;
    }

    @Override
    @Transactional
    public Report save(Report report) {
        if (!report.isNew()) {
            return null;
        }
        return crudReportRepository.save(report);
    }

    @Override
    public boolean delete(int userId) {
        return crudReportRepository.deleteReportsByUserId(userId) != 0;
    }

    @Override
    public List<Report> getAllByUserId(int userId) {
        return crudReportRepository.getAllByUserId(userId);
    }

    public List<Report> getAllByRestaurantId(int restaurantId) {
        return crudReportRepository.getAllByRestaurantId(restaurantId);
    }
}
