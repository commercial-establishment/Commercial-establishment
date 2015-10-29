package kz.hts.ce.service;

import kz.hts.ce.entity.Product;
import kz.hts.ce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseService<Product, ProductRepository>{

    @Autowired
    protected ProductService(ProductRepository repository) {
        super(repository);
    }

}
