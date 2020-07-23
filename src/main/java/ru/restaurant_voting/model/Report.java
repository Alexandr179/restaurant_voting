package ru.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "reports")
public class Report extends AbstractBaseEntity {

    @Column(name = "user_link", nullable = false, columnDefinition = "bool default 0")
    private Integer userLink;


    @Column(name = "date_time", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dateTime = new Date();//                        LocalDateTime -caused error


    @Column(name = "restaurant_link", nullable = false, columnDefinition = "bool default 0")
    private Integer restaurantLink;


    public Report() {
    }


    public Report(Integer id, Integer userLink, Integer restaurantLink) {
        this(id, userLink, new Date(), restaurantLink);
    }

    public Report(Integer id, Integer userLink, Date dateTime, Integer restaurantLink) {
        super(id);
        this.userLink = userLink;
        this.dateTime = dateTime;
        this.restaurantLink = restaurantLink;
    }

    public Integer getUserLink() {
        return userLink;
    }

    public void setUserLink(Integer userId) {
        this.userLink = userId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getRestaurantLink() {
        return restaurantLink;
    }

    public void setRestaurantLink(Integer restaurantId) {
        this.restaurantLink = restaurantId;
    }

    @Override
    public String toString() {
        return "Report{" +
                "userLink=" + userLink +
                ", dateTime=" + dateTime +
                ", restaurantLink=" + restaurantLink +
                ", id=" + id +
                '}';
    }
}