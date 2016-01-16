package kz.hts.ce.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Warehouse extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;

    /*TODO nullable = false*/
    @Column(name = "import_date", nullable = true)
    private LocalDateTime importDate;

    private int arrival;
    private int residue;

    public int getArrival() {
        return arrival;
    }

    public void setArrival(int arrival) {
        this.arrival = arrival;
    }

    public int getResidue() {
        return residue;
    }

    public void setResidue(int residue) {
        this.residue = residue;
    }

    public LocalDateTime getImportDate() {
        return importDate;
    }

    public void setImportDate(LocalDateTime importDate) {
        this.importDate = importDate;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
