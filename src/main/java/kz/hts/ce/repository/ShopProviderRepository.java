package kz.hts.ce.repository;

import kz.hts.ce.entity.ShopProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShopProviderRepository extends JpaRepository<ShopProvider, UUID> {

    List<ShopProvider> findByProvider_Id(UUID id);

    ShopProvider findByProvider_IdAndShop_Id(UUID providerId, UUID shopId);

    @Transactional
    @Modifying
    @Query("UPDATE ShopProvider sp set sp.blocked = TRUE where sp.id = ?1")
    void lockById(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE ShopProvider sp set sp.blocked = FALSE where sp.id = ?1")
    void reestablishById(UUID id);
}
