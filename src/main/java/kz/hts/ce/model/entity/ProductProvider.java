package kz.hts.ce.model.entity;

import org.hibernate.annotations.Proxy;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name = "product_provider")
@Audited
@Proxy(lazy = false)
public class ProductProvider extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "provider_id", referencedColumnName = "id")
    private Provider provider;

    @Column(name = "is_blocked")
    private boolean blocked;

    @Transient
    private Map<String, Integer> limits;

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

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Map<String, Integer> getLimits() {
        return limits;
    }

    public void setLimits(String limits) {
        if (this.limits == null) this.limits = new LinkedHashMap<>();
        String[] splittedLimitsByComma = limits.split(",");
        for (String limit : splittedLimitsByComma) {
            String[] splittedLimitByColon = limit.split(":");
            this.limits.put(splittedLimitByColon[0], Integer.valueOf(splittedLimitByColon[1]));
        }
    }
}

