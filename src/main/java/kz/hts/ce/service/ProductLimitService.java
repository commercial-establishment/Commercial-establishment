package kz.hts.ce.service;

import kz.hts.ce.model.entity.ProductLimit;
import kz.hts.ce.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductLimitService extends BaseService<ProductLimit, ProductLimitRepository> {

    @Autowired
    protected ProductLimitService(ProductLimitRepository repository) {
        super(repository);
    }

    public List<ProductLimit> findByProductProviderId(UUID id) {
        return repository.findByProductProvider_Id(id);
    }


    public ProductLimit findByProductProviderIdAndTypeName(UUID id, String typeName) {
        return repository.findByProductProvider_IdAndType_Name(id, typeName);
    }
}
