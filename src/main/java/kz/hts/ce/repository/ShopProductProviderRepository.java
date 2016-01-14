package kz.hts.ce.repository;

import kz.hts.ce.model.entity.ProductResidue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShopProductProviderRepository extends JpaRepository<ProductResidue, UUID> {/*FIXME remove??*/

    List<ProductResidue> findByShop_IdAndProductProvider_Provider_Id(UUID shopId, UUID providerId);

    ProductResidue findByShop_IdAndProductProvider_Id(UUID shopId, UUID productProviderId);
}
