package kz.hts.ce.entity;

import javax.persistence.*;

@Entity
@Table(name = "product_provider")
public class ProductProvider extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    private long amount;

    @Column(name = "is_blocked")
    private boolean blocked;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}

