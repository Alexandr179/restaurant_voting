package ru.restaurant_voting.util;

import org.javamoney.moneta.Money;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.restaurant_voting.repository.RestaurantRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class MoneyUtil {

    public String toString(Money money) {
        return money == null ? "" : money.toString();
    }

    public static @Nullable Money parseMoney (@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : Money.parse(str);
    }
}
