package kz.hts.ce.service;

import kz.hts.ce.model.entity.ProductLimit;
import kz.hts.ce.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductLimitService extends BaseService<ProductLimit, ProductLimitRepository> {

    @Autowired
    protected ProductLimitService(ProductLimitRepository repository) {
        super(repository);
    }
}
