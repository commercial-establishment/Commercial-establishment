package kz.hts.ce.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_residue")
public class ProductResidue extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "product_provider_id", referencedColumnName = "id")
    private ProductProvider productProvider;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "shop_id", referencedColumnName = "id")
    private Shop shop;

    private int residue;

    @Column(name = "is_blocked")
    private boolean blocked;

    public ProductProvider getProductProvider() {
        return productProvider;
    }

    public void setProductProvider(ProductProvider productProvider) {
        this.productProvider = productProvider;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public int getResidue() {
        return residue;
    }

    public void setResidue(int residue) {
        this.residue = residue;
    }
}
