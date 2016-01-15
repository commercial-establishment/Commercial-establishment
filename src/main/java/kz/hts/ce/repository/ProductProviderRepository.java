package kz.hts.ce.repository;

import kz.hts.ce.model.entity.ProductProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductProviderRepository extends RevisionRepository<ProductProvider, UUID, Integer>, JpaRepository<ProductProvider, UUID> {

    List<ProductProvider> findByProvider_Id(UUID id);

    ProductProvider findByProvider_IdAndProduct_Id(UUID providerId, UUID productId);

    @Transactional
    @Modifying
    @Query("UPDATE ProductProvider pp set pp.blocked = TRUE where pp.id = ?1")
    void lockById(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE ProductProvider pp set pp.blocked = FALSE where pp.id = ?1")
    void reestablishById(UUID id);
}