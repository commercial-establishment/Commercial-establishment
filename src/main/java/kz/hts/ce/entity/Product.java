package kz.hts.ce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Product extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "model_id")
    private Model model;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
