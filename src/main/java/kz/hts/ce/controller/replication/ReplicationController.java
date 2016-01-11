package kz.hts.ce.controller.replication;

import kz.hts.ce.model.dto.ShopProductProviderDto;
import kz.hts.ce.model.entity.*;
import kz.hts.ce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReplicationController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private ShopProductProviderService sppService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductProviderService productProviderService;

    @RequestMapping(value = "/replication/residues", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void getProvidersFromClient(@RequestBody List<ShopProductProviderDto> sppDtos) {
        for (ShopProductProviderDto sppDto : sppDtos) {
            ShopProductProvider shopProductProvider = sppService.
                    findByShopIdAndProductProviderId(sppDto.getShopId(), sppDto.getProductProviderId());
            if (shopProductProvider == null) {
                ShopProductProvider spp = new ShopProductProvider();
                Shop shop = shopService.findById(sppDto.getShopId());
                spp.setShop(shop);
                ProductProvider productProvider = productProviderService.findById(sppDto.getProductProviderId());
                spp.setProductProvider(productProvider);
                spp.setResidue(sppDto.getResidue());
                spp.setBlocked(false);
                sppService.save(spp);
            } else {
                shopProductProvider.setResidue(sppDto.getResidue());
                sppService.save(shopProductProvider);
            }
        }
    }

    @RequestMapping(value = "/replication/product-provider-list/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public List<ProductProvider> getProductProviderListForSavingFromClient(@RequestBody List<ProductProvider> productProviderList) {
        for (ProductProvider productProvider : productProviderList) {
            if (productService.findById(productProvider.getProduct().getId()) == null) {
                productService.save(productProvider.getProduct());
            }
            if (providerService.findById(productProvider.getProvider().getId()) == null) {
                providerService.save(productProvider.getProvider());
            }
            productProviderService.save(productProvider);
        }
        return productProviderList;
    }
}
