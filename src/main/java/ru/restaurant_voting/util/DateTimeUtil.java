package ru.restaurant_voting.util;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.restaurant_voting.model.Role;
import ru.restaurant_voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    private static final LocalTime CONTROL_TIME = LocalTime.of(11, 00);

    private final RestaurantRepository restaurantRepository;

    public DateTimeUtil(RestaurantRepository restaurantRepository) {
       this.restaurantRepository = restaurantRepository;
    }

    public void checkVoting(Date currentDate, int id){
        if(getCreateVotingTime(id).getDate() == currentDate.getDate() &
                currentDate.getHours() > CONTROL_TIME.getHour() &&
                currentDate.getMinutes() > CONTROL_TIME.getMinute()){
            restaurantRepository.get(id).getUser().setrestaurantIdVoting(0);
        } else {
            return;// current Time is more CONTROL_TIME
        }
        restaurantRepository.get(id).getUser().setrestaurantIdVoting(id);
        setNewCreateVotingTime(id);
    }

    public Date getCreateVotingTime(int restaurant_id){
        return restaurantRepository.get(restaurant_id).getUser().getCreateVotingTime();
    }

    public boolean hasUserRole(int restaurant_id){// create if not use Security
        return restaurantRepository.get(restaurant_id).getUser().getRoles().contains(Role.USER);
    }

    public void setNewCreateVotingTime(int restaurant_id){
        restaurantRepository.get(restaurant_id).getUser().setCreateVotingTime(new Date());
    }

    // ------------------ old Time methods: --------------------

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
