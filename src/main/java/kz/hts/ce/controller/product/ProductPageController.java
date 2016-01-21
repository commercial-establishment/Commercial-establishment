package kz.hts.ce.controller.product;

import kz.hts.ce.model.entity.*;
import kz.hts.ce.service.*;
import kz.hts.ce.util.SpringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductPageController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private ProductProviderService productProviderService;
    @Autowired
    private ShopProductProviderService sppService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductLimitService productLimitService;

    @Autowired
    private SpringHelper springHelper;

    @RequestMapping("/admin/products/{id}")
    public String information(Model model, @PathVariable UUID id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "/product-info";
    }

    @RequestMapping("/admin/products/{id}/edit")
    public String edit(Model model, @PathVariable UUID id) {
        List<Category> categories = categoryService.findAll();
        List<Unit> units = unitService.findAll();
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("units", units);
        return "product-edit";
    }

    @RequestMapping("/admin/products/create")
    public String createForAdmin(Model model) {
        return create(model);
    }

    private String create(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Unit> units = unitService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("units", units);
        return "product-create";
    }

    @RequestMapping(value = "/admin/products", method = RequestMethod.GET)
    public String shopsPage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/provider/products/create")
    public String createForProvider(Model model, @ModelAttribute("productProvider") ProductProvider productProvider) {
        model.addAttribute("types", springHelper.getTypes());
        return create(model);
    }

    @RequestMapping(value = "/admin/providers/{id}/products", method = RequestMethod.GET)
    public String products(Model model, @PathVariable UUID id) {
        List<ProductProvider> providerProducts = productProviderService.findByProviderId(id);
        model.addAttribute("providerProducts", providerProducts);
        model.addAttribute("providerId", id);
        return "provider-products";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/add", method = RequestMethod.GET)
    public String providerProductAdd(Model model, @PathVariable("providerId") String providerId) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("providerId", providerId);
        return "provider-product-add";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/{productProviderId}", method = RequestMethod.GET)
    public String providerProducts(Model model, @PathVariable("productProviderId") UUID productProviderId) {
        ProductProvider productProvider = productProviderService.findById(productProviderId);
        model.addAttribute("productProvider", productProvider);
        return "provider-product-info";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/{productProviderId}/edit", method = RequestMethod.GET)
    public String providerProductEdit(Model model, @PathVariable("productProviderId") UUID productProviderId) {
        ProductProvider productProvider = productProviderService.findById(productProviderId);

        model.addAttribute("productProvider", productProvider);
        return "provider-product-edit";
    }
    @RequestMapping(value = "/provider/shops/{shopId}/products", method = RequestMethod.GET)
    public String providerShopProducts(Model model, @PathVariable("shopId") UUID shopId) {
        UUID providerId = springHelper.getAuthProviderId();
        Map<ProductProvider, Integer> products = sppService.findProductsByShopIdAndProviderId(shopId, providerId);
        Type shopType = shopService.findById(shopId).getType();
        Map<ProductLimit, Integer> productLimitResidueMap = new HashMap<>();
        for (Map.Entry<ProductProvider, Integer> productProvider : products.entrySet()) {
            ProductLimit productLimit = productLimitService.findByProductProviderIdAndTypeName(productProvider.getKey().getId(), shopType.getName());
            if (productLimit == null) {
                ProductLimit productLimitEntity = new ProductLimit();
                productLimitEntity.setMin(10);/*FIXME min max*/
                productLimitEntity.setMax(20);
                productLimitEntity.setType(shopType);
                productLimitEntity.setProductProvider(productProvider.getKey());
                productLimitResidueMap.put(productLimitEntity, productProvider.getValue());
            } else {
                if (productLimit.getMin() == 0 && productLimit.getMax() == 0) {
                    productLimit.setMin(10);
                    productLimit.setMin(20);
                }
                productLimitResidueMap.put(productLimit, productProvider.getValue());
            }
        }
        model.addAttribute("productLimitResidueMap", productLimitResidueMap);
        return "provider-shop-products";
    }

    @RequestMapping(value = "/provider/products", method = RequestMethod.GET)
    public String providerProducts(Model model) {
        List<ProductProvider> productProviderList = productProviderService.findByProviderId(springHelper.getAuthProviderId());
        model.addAttribute("productProviderList", productProviderList);
        return "products";
    }

    @RequestMapping(value = "/provider/products/{productId}", method = RequestMethod.GET)
    public String providerProductInfo(Model model, @PathVariable("productId") UUID productId) {
        UUID providerId = springHelper.getAuthProviderId();
        UUID productProviderId = productProviderService.findByProviderIdAndProductId(providerId, productId).getId();
        List<ProductLimit> productLimits = productLimitService.findByProductProviderId(productProviderId);
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("limits", productLimits);
        return "product-info";
    }
}
