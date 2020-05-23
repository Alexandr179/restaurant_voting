package ru.restaurant_voting;

//import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import static org.hamcrest.CoreMatchers.is;

//import org.junit.Test;

public class MoneyTest {

    @Test
    public void testMoneyApi() {
        MonetaryAmount eurAmount1 = Monetary.getDefaultAmountFactory().setNumber(1.1111).setCurrency("EUR").create();
        MonetaryAmount eurAmount2 = Monetary.getDefaultAmountFactory().setNumber(1.1141).setCurrency("EUR").create();

//        MonetaryAmount eurAmount3 = eurAmount1.add(eurAmount2);
//        assertThat(eurAmount3.toString(), is("EUR 2.2252"));
//
//        MonetaryRounding defaultRounding = Monetary.getDefaultRounding();
//        MonetaryAmount eurAmount4 = eurAmount3.with(defaultRounding);
//        assertThat(eurAmount4.toString(), is("EUR 2.23"));
//
//        MonetaryAmountFormat germanFormat = MonetaryFormats.getAmountFormat(Locale.GERMAN);
//        assertThat(germanFormat.format(eurAmount4), is("EUR 2,23") );
    }
}