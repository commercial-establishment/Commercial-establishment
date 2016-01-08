package kz.hts.ce.service;

import kz.hts.ce.entity.ShopProvider;
import kz.hts.ce.repository.ShopProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopProviderService extends BaseService<ShopProvider, ShopProviderRepository> {

    @Autowired
    protected ShopProviderService(ShopProviderRepository repository) {
        super(repository);
    }

    public List<ShopProvider> findByProviderId(UUID id) {
       return repository.findByProvider_Id(id);
    }

    public ShopProvider findByProviderIdAndShopId(UUID providerId, UUID shopId) {
       return repository.findByProvider_IdAndShop_Id(providerId, shopId);
    }

    public void lockById(UUID id) {
        repository.lockById(id);
    }

    public void reestablishById(UUID id) {
        repository.reestablishById(id);
    }
}
