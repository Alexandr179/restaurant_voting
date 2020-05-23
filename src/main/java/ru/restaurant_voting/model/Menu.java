package ru.restaurant_voting.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.restaurant_voting.View;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "menus")
public class Menu extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
//    @Range(min = 10, max = 100000)
    private Integer price;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Menu() {
    }

    public Menu(Menu m) {// from Test's
        this(m.getId(), m.getName(), m.getPrice());
    }

    public Menu(String name, Integer price) {
        this(null, name, price);
    }

    public Menu(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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