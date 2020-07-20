package ru.restaurant_voting.web.converter;

import org.javamoney.moneta.Money;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import static ru.restaurant_voting.util.MoneyUtil.parseMoney;

public class MoneyFormatter implements Formatter<Money> {

    @Override
    public String print(Money object, Locale locale) {
        return null;
    }

    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        return parseMoney(text);
    }
}
