package ru.restaurant_voting.model;

import org.javamoney.moneta.Money;

import javax.persistence.*;


@Entity
@Table(name = "menus")
public class Menu extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
//    @Range(min = 10, max = 100000)
    private Money price;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Menu() {
    }

    public Menu(String name, Money price) {
        this(null, name, price);
    }

    public Menu(Integer id, String name, Money price) {
        super(id, name);
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public String toString() {
        return "Menu{" +
                "price=" + price +
                ", restaurant=" + restaurant +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}