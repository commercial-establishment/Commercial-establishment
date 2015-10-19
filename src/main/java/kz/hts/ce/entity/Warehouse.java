package kz.hts.ce.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Warehouse extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    private int totalQuantity;
    private int quantity;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
