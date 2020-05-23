package ru.restaurant_voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.CollectionUtils;
import ru.restaurant_voting.HasIdAndEmail;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User extends AbstractNamedEntity implements HasIdAndEmail {

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;


    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;


    @Column(name = "restaurant_id_voting", nullable = false, columnDefinition = "bool default 0")
    private Integer restaurantIdVoting;


    @Column(name = "createVotingTime", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date createVotingTime = new Date();


    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique_idx")})
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
//    @Fetch(FetchMode.SUBSELECT)
    @BatchSize(size = 200)
    private Set<Role> roles;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("name DESC")
    @JsonIgnore
    private List<Restaurant> restaurants;


    public User() {
    }

    public User(User u) {// from Test's
        this(u.getId(), u.getName(), u.getEmail(), u.getPassword(), u.isEnabled(), u.getrestaurantIdVoting(), u.getCreateVotingTime(), u.getRoles());
    }

    public User(Integer id, String name, String email, String password, Integer restaurantIdVoting, Role role, Role... roles) {
        this(id, name, email, password,true, restaurantIdVoting, new Date(), EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String email, String password, boolean enabled, Integer restaurantIdVoting, Date createVotingTime, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.restaurantIdVoting = restaurantIdVoting;
        this.createVotingTime = createVotingTime;
        setRoles(roles);
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateVotingTime() {
        return createVotingTime;
    }

    public void setCreateVotingTime(Date createVotingTime) {
        this.createVotingTime = createVotingTime;
    }

    public Integer getrestaurantIdVoting() {
        return restaurantIdVoting;
    }

    public void setrestaurantIdVoting(int restaurantIdVoting) {
        this.restaurantIdVoting = restaurantIdVoting;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", createVotingTime=" + createVotingTime +
                ", roles=" + roles +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}