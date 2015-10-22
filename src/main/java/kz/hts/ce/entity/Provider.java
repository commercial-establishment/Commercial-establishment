package kz.hts.ce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Provider extends BaseEntity {

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
