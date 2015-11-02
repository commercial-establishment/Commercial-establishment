package kz.hts.ce.controller.product;

import kz.hts.ce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProductPageController {

    @Autowired
    private ProductService productService;
}
