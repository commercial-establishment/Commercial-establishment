package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.ProductProvider;
import kz.hts.ce.service.ProductProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductProviderReplicationController {

    @Autowired
    private ProductProviderService productProviderService;

    @RequestMapping(value = "/replication/product-provider-list", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void getProductProviderListForSavingFromClient(@RequestBody List<ProductProvider> productProviderList) {
        for (ProductProvider productProvider : productProviderList) productProviderService.save(productProvider);
    }

    @RequestMapping(value = "/replication/product-provider-list/time={time}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<ProductProvider> sendNewProductProviderListDataToClient(@PathVariable long time) {
        List<ProductProvider> productProviderList;
        if (time == 0) productProviderList = productProviderService.findAll();
        else productProviderList = productProviderService.getHistory(time);
        return productProviderList;
    }
}
