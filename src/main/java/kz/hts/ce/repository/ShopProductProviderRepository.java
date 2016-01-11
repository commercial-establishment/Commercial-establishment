package kz.hts.ce.repository;

import kz.hts.ce.model.entity.ShopProductProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShopProductProviderRepository extends JpaRepository<ShopProductProvider, UUID> {

    List<ShopProductProvider> findByShop_IdAndProductProvider_Provider_Id(UUID shopId, UUID providerId);

    ShopProductProvider findByShop_IdAndProductProvider_Id(UUID shopId, UUID productProviderId);
}
