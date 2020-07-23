package ru.restaurant_voting.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.restaurant_voting.model.Report;
import ru.restaurant_voting.repository.ReportRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private static final LocalTime CONTROL_TIME = LocalTime.of(11, 00);
    public static final LocalDate TODAY = LocalDate.now();

    @Autowired
    private static ReportRepository reportRepository;

    public DateTimeUtil(ReportRepository reportRepository) {
        DateTimeUtil.reportRepository = reportRepository;
    }

    public static boolean isVotingTodayReportBy(int restaurantId) {//   in Report exists ..restaurantLink
        Optional<Report> reportOpt = reportRepository.getByRestaurantId(restaurantId);
        if(reportOpt.isPresent()){
            int day = reportOpt.get().getDateTime().getDate();
            int today = TODAY.getDayOfMonth();
            return day == today;
        } return false;
    }

    public static boolean isVotingBeforeControlTimeBy(int restaurantId) {
        Optional<Report> reportOpt = reportRepository.getByRestaurantId(restaurantId);
        return CONTROL_TIME.getHour() >= reportOpt.get().getDateTime().getHours() &
               CONTROL_TIME.getMinute() >=  reportOpt.get().getDateTime().getMinutes();
    }

    public String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static @Nullable
    LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static @Nullable LocalTime parseLocalTime(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }
}