package kz.hts.ce.service;

import kz.hts.ce.model.entity.Product;
import kz.hts.ce.model.entity.ProductResidue;
import kz.hts.ce.repository.ShopProductProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShopProductProviderService extends BaseService<ProductResidue, ShopProductProviderRepository> {

    @Autowired
    protected ShopProductProviderService(ShopProductProviderRepository repository) {
        super(repository);
    }

    public Map<Product, Integer> findProductsByShopIdAndProviderId(UUID shopId, UUID providerId) {
        List<ProductResidue> shopProviderProducts = repository.findByShop_IdAndProductProvider_Provider_Id(shopId, providerId);
        Map<Product, Integer> products = new HashMap<>();
        for (ProductResidue shopProviderProduct : shopProviderProducts) {
            products.put(shopProviderProduct.getProductProvider().getProduct(), shopProviderProduct.getResidue());
        }
        return products;
    }

    public List<ProductResidue> findByShopIdAndProviderId(UUID shopId, UUID providerId) {
        return repository.findByShop_IdAndProductProvider_Provider_Id(shopId, providerId);
    }

    public ProductResidue findByShopIdAndProductProviderId(UUID shopId, UUID productProviderId) {
        return repository.findByShop_IdAndProductProvider_Id(shopId, productProviderId);
    }
}
