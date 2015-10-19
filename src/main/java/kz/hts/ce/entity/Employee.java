package kz.hts.ce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee extends User {

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
