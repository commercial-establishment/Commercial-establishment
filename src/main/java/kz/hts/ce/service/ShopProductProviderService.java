package kz.hts.ce.service;

import kz.hts.ce.model.entity.Product;
import kz.hts.ce.model.entity.ShopProductProvider;
import kz.hts.ce.repository.ShopProductProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopProductProviderService extends BaseService<ShopProductProvider, ShopProductProviderRepository> {

    @Autowired
    protected ShopProductProviderService(ShopProductProviderRepository repository) {
        super(repository);
    }

    public Map<Product, Integer> findProductsByShopIdAndProviderId(UUID shopId, UUID providerId) {
        List<ShopProductProvider> shopProviderProducts = repository.findByShop_IdAndProductProvider_Provider_Id(shopId, providerId);
        Map<Product, Integer> products = new HashMap<>();
        for (ShopProductProvider shopProviderProduct : shopProviderProducts) {
            products.put(shopProviderProduct.getProductProvider().getProduct(), shopProviderProduct.getResidue());
        }
        return products;
    }

    public List<ShopProductProvider> findByShopIdAndProviderId(UUID shopId, UUID providerId) {
        return repository.findByShop_IdAndProductProvider_Provider_Id(shopId, providerId);
    }
}
