package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.Product;
import kz.hts.ce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductReplicationController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/replication/products", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void saveNewProvidersDataFromClient(@RequestBody List<Product> products) {
        for (Product product : products) productService.save(product);
    }

    @RequestMapping(value = "/replication/products/time={time}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Product> sendNewProductsDataToClient(@PathVariable long time) {
        List<Product> products;
        if (time == 0) products = productService.findAll();
        else products = productService.getHistory(time);
        return products;
    }
}
