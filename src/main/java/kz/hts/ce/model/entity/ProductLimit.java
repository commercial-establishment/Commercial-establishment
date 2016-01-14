package kz.hts.ce.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "product_limit")
public class ProductLimit extends BaseEntity {

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "product_provider_id", referencedColumnName = "id")
    private ProductProvider productProvider;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

    private int min;
    private int max;

    public ProductProvider getProductProvider() {
        return productProvider;
    }

    public void setProductProvider(ProductProvider productProvider) {
        this.productProvider = productProvider;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
