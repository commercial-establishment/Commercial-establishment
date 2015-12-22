package kz.hts.ce.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Shop extends BaseEntity {

    @NotEmpty
    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shop")
    private List<Employee> employees;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @OneToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Column(name = "is_blocked", nullable = false)
    private boolean blocked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
