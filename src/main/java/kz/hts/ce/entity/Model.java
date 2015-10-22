package kz.hts.ce.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Model extends BaseEntity {

    /*TODO annotations*/
    private String name;

    @OneToOne
    @JoinColumn(name = "image_id", nullable = false)
    private Image image;

    @ManyToMany(mappedBy="models")
    private List<Provider> providers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }
}
