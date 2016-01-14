package kz.hts.ce.controller.replication;

import kz.hts.ce.model.dto.ShopProductProviderDto;
import kz.hts.ce.model.entity.ProductProvider;
import kz.hts.ce.model.entity.Shop;
import kz.hts.ce.model.entity.ProductResidue;
import kz.hts.ce.service.ProductProviderService;
import kz.hts.ce.service.ShopProductProviderService;
import kz.hts.ce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ResidueReplicationController {

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
            ProductResidue productResidue = sppService.
                    findByShopIdAndProductProviderId(sppDto.getShopId(), sppDto.getProductProviderId());
            if (productResidue == null) {
                ProductResidue spp = new ProductResidue();
                Shop shop = shopService.findById(sppDto.getShopId());
                spp.setShop(shop);
                ProductProvider productProvider = productProviderService.findById(sppDto.getProductProviderId());
                spp.setProductProvider(productProvider);
                spp.setResidue(sppDto.getResidue());
                spp.setBlocked(false);
                sppService.save(spp);
            } else {
                productResidue.setResidue(sppDto.getResidue());
                sppService.save(productResidue);
            }
        }
    }
}
