package kz.hts.ce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Shop extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;
}
