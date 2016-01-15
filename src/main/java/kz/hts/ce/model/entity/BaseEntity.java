package kz.hts.ce.model.entity;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(columnDefinition = "uuid")
    @org.hibernate.annotations.Type(type="pg-uuid")
    private UUID id;

    @PrePersist//FIXME
    public void initializeUUID() {
        if (id == null) id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
