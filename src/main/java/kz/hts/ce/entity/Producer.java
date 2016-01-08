package kz.hts.ce.entity;

import javax.persistence.Entity;

@Entity/*TODO for what this class??*/
public class Producer extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
