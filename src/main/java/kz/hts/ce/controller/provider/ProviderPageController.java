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
    private ShopProviderService shopProviderService;
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

    @RequestMapping(value = "/admin/providers/{id}/shops", method = RequestMethod.GET)
    public String shops(Model model, @PathVariable UUID id) {
        List<ShopProvider> providerShops = shopProviderService.findByProviderId(id);
        model.addAttribute("providerShops", providerShops);
        model.addAttribute("providerId", id);
        return "provider-shops";
    }

    @RequestMapping(value = "/admin/providers/{providerId}/shops/all", method = RequestMethod.GET)
    public String providerShopAdd(Model model, @PathVariable("providerId") String providerId) {
        List<Shop> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        model.addAttribute("providerId", providerId);
        return "provider-shop-add";
    }

    @RequestMapping(value = "/provider/shops", method = RequestMethod.GET)
    public String providerShops(Model model) {
        List<ShopProvider> providerShops = shopProviderService.findByProviderId(springHelper.getAuthProviderId());
        List<Shop> shops = new ArrayList<>();
        for (ShopProvider providerShop : providerShops) {
            shops.add(providerShop.getShop());
        }
        UUID providerId = springHelper.getAuthProviderId();
        Map<Shop, String> shopMap = new HashMap<>();
        List<String> colors = new ArrayList<>();
        for (Shop shop : shops) {
            Map<ProductProvider, Integer> products = sppService.findProductsByShopIdAndProviderId(shop.getId(), providerId);
            Type shopType = shopService.findById(shop.getId()).getType();
            Map<ProductLimit, Integer> productLimitResidueMap = new HashMap<>();
            for (Map.Entry<ProductProvider, Integer> productProvider : products.entrySet()) {
                ProductLimit productLimit = productLimitService.findByProductProviderIdAndTypeName(productProvider.getKey().getId(), shopType.getName());
                Integer residue = productProvider.getValue();
                int min;
                int max;
                if (productLimit == null) {
                    min = 10;
                    max = 20;
                } else {
                    min = productLimit.getMin();
                    max = productLimit.getMax();
                }

                String color = getRowColor(residue, min, max);
                colors.add(color);
            }

            if (colors.contains("red")) {
                shopMap.put(shop, "red");
                colors.clear();
            } else if (colors.contains("orange") && !colors.contains("red")) {
                shopMap.put(shop, "orange");
                colors.clear();
            } else if (!colors.contains("red") && !colors.contains("orange")){
                shopMap.put(shop, "green");
                colors.clear();
            }
        }
        model.addAttribute("shops", shopMap);
        return "shops";
    }

    private String getRowColor(Integer residue, int min, int max) {
        if (residue < min) return "red";
        else if (min <= residue && residue < max) return "orange";
        else return "green";
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
}
