package kz.hts.ce.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Provider extends BaseEntity {

    private String username;
    private String password;
    private String address;
    private String email;

    @Column(name = "contact_person")
    private String contactPerson;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Column(name = "start_work_date", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startWorkDate;

    @Column(name = "end_work_date", nullable = true)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endWorkDate;

    @Column(name = "is_blocked", nullable = false)
    private boolean blocked;

    @ManyToMany
    @JoinTable(
            name="PROVIDER_PRODUCT",
            joinColumns={@JoinColumn(name="PROVIDER_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID")})
    private List<Product> products;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getStartWorkDate() {
        return startWorkDate;
    }

    public void setStartWorkDate(Date startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    public Date getEndWorkDate() {
        return endWorkDate;
    }

    public void setEndWorkDate(Date endWorkDate) {
        this.endWorkDate = endWorkDate;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
}
