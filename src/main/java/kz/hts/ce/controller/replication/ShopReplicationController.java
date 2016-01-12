package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.Shop;
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
public class ShopReplicationController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/replication/shops", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void getShopsForSavingFromClient(@RequestBody List<Shop> shops) {
        for (Shop shop : shops) shopService.save(shop);
    }
}
