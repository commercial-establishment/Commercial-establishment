package kz.hts.ce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Model extends BaseEntity {

    /*TODO annotations*/
    private String name;

    @OneToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;


}
