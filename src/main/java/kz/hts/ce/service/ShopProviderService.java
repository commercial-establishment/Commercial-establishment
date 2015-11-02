package kz.hts.ce.service;

import kz.hts.ce.entity.ShopProvider;
import kz.hts.ce.repository.ShopProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopProviderService extends BaseService<ShopProvider, ShopProviderRepository> {

    @Autowired
    protected ShopProviderService(ShopProviderRepository repository) {
        super(repository);
    }

    public List<ShopProvider> findByProviderId(long id) {
       return repository.findByProvider_Id(id);
    }
}