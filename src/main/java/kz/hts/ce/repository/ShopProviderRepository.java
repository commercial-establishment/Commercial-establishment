package kz.hts.ce.repository;

import kz.hts.ce.entity.ShopProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProviderRepository extends JpaRepository<ShopProvider, Long> {

    List<ShopProvider> findByProvider_Id(long id);
}
