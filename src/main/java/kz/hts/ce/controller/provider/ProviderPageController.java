package kz.hts.ce.controller.provider;

import kz.hts.ce.model.entity.*;
import kz.hts.ce.service.*;
import kz.hts.ce.util.SpringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class ProviderPageController {

    private static final String PROVIDER = "PROVIDER";

    @Autowired
    private ProviderService providerService;
    @Autowired
    private CityService cityService;
    @Autowired
    private ProductProviderService productProviderService;
    @Autowired
    private ShopProviderService shopProviderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopProductProviderService sppService;
    @Autowired
    private ProductLimitService productLimitService;

    @Autowired
    private SpringHelper springHelper;

    @RequestMapping(value = "/admin/providers", method = RequestMethod.GET)
    public String providers(Model model) {
        List<Provider> providers = providerService.findByRoleName(PROVIDER);
        model.addAttribute("providers", providers);
        return "providers";
    }

    @RequestMapping("/admin/providers/{id}")
    public String providerInformation(Model model, @PathVariable UUID id) {
        Provider provider = providerService.findById(id);
        model.addAttribute("provider", provider);
        return "/provider-info";
    }

    @RequestMapping("/admin/providers/{id}/edit")
    public String edit(Model model, @PathVariable UUID id) {
        List<City> cities = cityService.findAll();
        Provider provider = providerService.findById(id);
        List<Role> roles = springHelper.getRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("provider", provider);
        model.addAttribute("cities", cities);
        return "provider-edit";
    }

    @RequestMapping("/admin/providers/create")
    public String create(Model model) {
        Provider provider = new Provider();
        List<City> cities = cityService.findAll();

        model.addAttribute("provider", provider);
        model.addAttribute("cities", cities);
        return "provider-create";
    }

    @RequestMapping(value = "/admin/providers/{id}/products", method = RequestMethod.GET)
    public String products(Model model, @PathVariable UUID id) {
        List<ProductProvider> providerProducts = productProviderService.findByProviderId(id);
        model.addAttribute("providerProducts", providerProducts);
        model.addAttribute("providerId", id);
        return "provider-products";
    }

    @RequestMapping(value = "/admin/providers/{id}/shops", method = RequestMethod.GET)
    public String shops(Model model, @PathVariable UUID id) {
        List<ShopProvider> providerShops = shopProviderService.findByProviderId(id);
        model.addAttribute("providerShops", providerShops);
        model.addAttribute("providerId", id);
        return "provider-shops";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/products/add", method = RequestMethod.GET)
    public String providerProductAdd(Model model, @PathVariable("providerId") String providerId) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("providerId", providerId);
        return "provider-product-add";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/shops/all", method = RequestMethod.GET)
    public String providerShopAdd(Model model, @PathVariable("providerId") String providerId) {
        List<Shop> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        model.addAttribute("providerId", providerId);
        return "provider-shop-add";
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

    @RequestMapping(value = "/provider/shops", method = RequestMethod.GET)
    public String providerShops(Model model) {
        List<ShopProvider> providerShops = shopProviderService.findByProviderId(springHelper.getAuthProviderId());
        List<Shop> shops = new ArrayList<>();
        for (ShopProvider providerShop : providerShops) {
            shops.add(providerShop.getShop());
        }
        model.addAttribute("shops", shops);
        return "shops";
    }

    @RequestMapping(value = "/provider/shops/add", method = RequestMethod.GET)
    public String providerAddShop(Model model) {
        UUID id = springHelper.getAuthProviderId();
        List<ShopProvider> providerShops = shopProviderService.findByProviderId(id);
        List<UUID> shopIds = new ArrayList<>();
        for (ShopProvider providerShop : providerShops) shopIds.add(providerShop.getShop().getId());

        List<Shop> allShops = shopService.findAll();
        for (UUID shopId : shopIds) {
            Iterator<Shop> it = allShops.iterator();
            it.hasNext();
            if (allShops.size() == 0) break;
            Shop shop = it.next();
            if (shop.getId().equals(shopId)) {
                allShops.remove(shop);
            }
        }

        model.addAttribute("providerId", id);
        model.addAttribute("shops", allShops);
        return "provider-shop-add";
    }

    @RequestMapping(value = "/provider/shops/{shopId}", method = RequestMethod.GET)
    public String providerShop(Model model, @PathVariable("shopId") UUID shopId) {
        Shop shop = shopService.findById(shopId);
        model.addAttribute("shop", shop);
        return "shop-info";
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
