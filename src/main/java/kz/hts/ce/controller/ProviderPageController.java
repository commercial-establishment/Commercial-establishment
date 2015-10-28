package kz.hts.ce.controller;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.Provider;
import kz.hts.ce.entity.Role;
import kz.hts.ce.service.ProviderService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static kz.hts.ce.util.SpringUtils.getPrincipal;

@Controller
public class ProviderPageController {

    public static final String PROVIDER = "PROVIDER";

    @Autowired
    private ProviderService providerService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/providers/{id}")
    public String providerInformationPage(Model model, @PathVariable long id){
        Provider provider = providerService.findById(id);
        model.addAttribute("provider", provider);
        return "/provider-info";
    }

    @RequestMapping(value = {"/","/provider"}, method = RequestMethod.GET)
    public String providerPage(Model model) {
        String username = getPrincipal();
        Provider provider = providerService.findByUsername(username);
        model.addAttribute("name", provider.getName());
        model.addAttribute("patronymic", provider.getPatronymic());
        return "admin";
    }
    @RequestMapping("/providers/{id}/edit")
    public String editProviderPage(Model model, @PathVariable long id){
        Provider provider = providerService.findById(id);
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("provider", provider);
        return "provider-edit";
    }

    @RequestMapping("/providers/create")
    public String edit(Model model){
        Provider provider = new Provider();
        model.addAttribute("provider", provider);
        return "provider-create";
    }

    @RequestMapping(value = "/providers", method = RequestMethod.GET)
    public String providersPage(Model model){
        List<Provider> providers = providerService.findByRoleName(PROVIDER);
        model.addAttribute("providers", providers);
        return "providers";
    }

    @RequestMapping(value = "?recovery", method = RequestMethod.GET)
    public String passwordRecoveryPage(){ return "password-recovery"; }
}
