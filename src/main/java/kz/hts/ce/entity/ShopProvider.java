package kz.hts.ce.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "shop_provider")
public class ShopProvider extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
