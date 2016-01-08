package kz.hts.ce.repository;

import kz.hts.ce.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ShopRepository extends JpaRepository<Shop, UUID> {

    @Transactional
    @Modifying
    @Query("UPDATE Shop s set s.blocked = TRUE where s.id = ?1")
    void lockById(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Shop s set s.blocked = FALSE where s.id = ?1")
    void reestablishById(UUID id);
}
