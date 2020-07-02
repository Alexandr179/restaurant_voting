package ru.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.util.CollectionUtils;
import ru.restaurant_voting.HasIdAndEmail;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "reports")
public class Reports extends AbstractBaseEntity {

    @Column(name = "user_id", nullable = false, columnDefinition = "bool default 0")
    private Integer userId;


    @Column(name = "voting_time", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createVotingTime = new Date();


    @Column(name = "restaurant_id", nullable = false, columnDefinition = "bool default 0")
    private Integer restaurantId;


    public Reports() {
    }


    public Reports(Reports r) {// from Test's
        this(r.id, r.userId, new Date(), r.restaurantId);
    }

    public Reports(Integer id, Integer userId, @NotNull Date createVotingTime, Integer restaurantId) {
        super(id);
        this.userId = userId;
        this.createVotingTime = createVotingTime;
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateVotingTime() {
        return createVotingTime;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Reports{" +
                "userId=" + userId +
                ", createVotingTime=" + createVotingTime +
                ", restaurantId=" + restaurantId +
                ", id=" + id +
                '}';
    }
}