package kz.hts.ce.repository;

import kz.hts.ce.model.entity.ProductLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductLimitRepository extends JpaRepository<ProductLimit, UUID> {

    List<ProductLimit> findByProductProvider_Id(UUID id);

    ProductLimit findByProductProvider_IdAndType_Name(UUID id, String typeName);
}
