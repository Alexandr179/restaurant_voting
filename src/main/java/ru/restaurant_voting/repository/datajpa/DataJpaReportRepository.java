package ru.restaurant_voting.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.restaurant_voting.model.Report;
import ru.restaurant_voting.repository.ReportRepository;

import java.util.List;
import java.util.Optional;


@Repository
public class DataJpaReportRepository implements ReportRepository {

    private final CrudReportRepository crudReportRepository;

    public DataJpaReportRepository(CrudReportRepository crudReportRepository) {
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
    public boolean deleteByUserId(int userId) {
        return crudReportRepository.deleteReportsByUserLink(userId) != 0;
    }

    @Override
    public boolean deleteById(int id) {
        return crudReportRepository.deleteReportsById(id) != 0;
    }

    public boolean deleteByRestaurantId(int restaurantId){
        return crudReportRepository.deleteReportByRestaurantLink(restaurantId) != 0;
    }

    @Override
    public List<Report> getAllByUserId(int userId) {
        return crudReportRepository.getAllByUserLink(userId);
    }

    @Override
    public Optional<Report> getByRestaurantId(int restaurantId) {
        return crudReportRepository.getFirstByRestaurantLink(restaurantId);
    }


    @Override
    public List<Report> getAllByRestaurantId(int restaurantId) {
        return crudReportRepository.getAllByRestaurantLink(restaurantId);
    }

//    @Override
//    public List<Report> getReportTodayBy(int authUserId, int restaurantId, int day){
//        return crudReportRepository.getReportByUserIdAndRestaurantIdAndDateTime_Day(authUserId, restaurantId, day);
//    }

    @Override
    public Report getById(Integer id) {
        return crudReportRepository.getOne(id);
    }
}
