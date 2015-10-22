package kz.hts.ce.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Shop extends BaseEntity {

    private String username;
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @OneToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;

    @Column(name = "is_blocked", nullable = false)
    private boolean blocked;

    @ManyToMany(mappedBy="shops")
    private List<Provider> providers;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
