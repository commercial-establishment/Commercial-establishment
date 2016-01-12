package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.ShopProvider;
import kz.hts.ce.service.ShopProviderService;
import org.hibernate.envers.Audited;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShopProviderReplicationController {

    @Audited
    private ShopProviderService shopProviderService;

    @RequestMapping(value = "/replication/shop-provider-list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void getProductProviderListForSavingFromClient(@RequestBody List<ShopProvider> shopProviderList) {
        for (ShopProvider shopProvider : shopProviderList) shopProviderService.save(shopProvider);
    }
}
