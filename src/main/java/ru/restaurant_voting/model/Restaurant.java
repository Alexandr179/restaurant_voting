package ru.restaurant_voting.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "restaurants_unique_phone_idx")})
public class Restaurant extends AbstractNamedEntity {

    @Column(name = "address")
    private String address;

    @Column(name = "phone", unique = true)
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("name DESC")
//    @JsonIgnore
    private List<Menu> menus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Restaurant() {
    }

    public Restaurant(Restaurant r) {// from Test's
        this(r.getId(), r.getName(), r.getAddress(), r.getPhone());
    }

    public Restaurant(String name, String address, String phone) {
        this(null, name, address, phone);
    }

    public Restaurant(Integer id, String name, String address, String phone) {
        super(id, name);
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public User getUser() {//https://stackoverflow.com/questions/33147853/onetoone-in-hibernate-unknown-mappedby/33147927
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "address='" + address + '\'' +
                ", phone=" + phone +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}