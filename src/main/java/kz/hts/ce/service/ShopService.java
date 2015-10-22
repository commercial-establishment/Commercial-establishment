package kz.hts.ce.service;

import kz.hts.ce.entity.Shop;
import kz.hts.ce.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService extends BaseService<Shop, ShopRepository> {

    @Autowired
    protected ShopService(ShopRepository repository) {
        super(repository);
    }
}
