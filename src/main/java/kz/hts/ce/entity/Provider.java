package kz.hts.ce.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Provider extends BaseEntity {

    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;

    /*TODO nullable = false*/
    @Column(name = "start_work_date", nullable = true)
    private LocalDateTime startWorkDate;

    /*TODO nullable = false*/
    @Column(name = "end_work_date", nullable = true)
    private LocalDateTime endWorkDate;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "is_blocked", nullable = false)
    private boolean blocked;

    @ManyToMany
    @JoinTable(
            name="PROVIDER_MODEL",
            joinColumns={@JoinColumn(name="PROVIDER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="MODEL_ID", referencedColumnName="ID")})
    private List<Model> models;

    @ManyToMany
    @JoinTable(
            name="SHOP_PROVIDER",
            joinColumns={@JoinColumn(name="PROVIDER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="SHOP_ID", referencedColumnName="ID")})
    private List<Shop> shops;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(LocalDateTime startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public LocalDateTime getEndWorkDate() {
        return endWorkDate;
    }

    public void setEndWorkDate(LocalDateTime endWorkDate) {
        this.endWorkDate = endWorkDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
