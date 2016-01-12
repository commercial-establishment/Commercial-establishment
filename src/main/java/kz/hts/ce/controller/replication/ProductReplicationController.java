package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.Product;
import kz.hts.ce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
