package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.ShopProvider;
import kz.hts.ce.service.ShopProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class ShopProviderReplicationController {

    @Autowired
    private ShopProviderService shopProviderService;

    @RequestMapping(value = "/replication/shop-provider-list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void getProductProviderListForSavingFromClient(@RequestBody List<ShopProvider> shopProviderList) {
        for (ShopProvider shopProvider : shopProviderList) shopProviderService.save(shopProvider);
    }

    @RequestMapping(value = "/replication/shops-provider-list/time={time}&shop={shopId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<ShopProvider> sendNewProvidersDataToClient(@PathVariable long time, @PathVariable UUID shopId) {
        List<ShopProvider> shopProviders;
        List<ShopProvider> shopProviderListByShopId = shopProviderService.findByShopId(shopId);
        if (time == 0) shopProviders = shopProviderListByShopId;
        else shopProviders = shopProviderService.getHistory(time, shopId, shopProviderListByShopId);
        return shopProviders;
    }
}
