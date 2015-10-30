package kz.hts.ce.controller.provider;


import kz.hts.ce.entity.*;
import kz.hts.ce.service.CityService;
import kz.hts.ce.service.ProviderService;
import kz.hts.ce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class ProviderController {

    public static final String PROVIDER = "PROVIDER";

    @Autowired
    private ProviderService providerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/providers/{id}/lock")
    public String lock(@PathVariable long id) {
        Provider provider = providerService.findById(id);
        provider.setEndWorkDate(new Date());
        provider.setBlocked(true);
        providerService.save(provider);
        providerService.updateEndWorkDate(new Date(), id);
        providerService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/providers/{id}/reestablish")
    public String reestablish(@PathVariable long id) {
        providerService.updateStartAndEndWorkDate(new Date(), null, id);
        providerService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/providers/{id}/edit", method = RequestMethod.POST)
    public String edit(Model model, @PathVariable long id, @Valid @ModelAttribute("provider") Provider provider, BindingResult result) {
        Role role = roleService.findByName(PROVIDER);
        provider.setRole(role);

        if (result.hasErrors()) {
            List<City> cities = cityService.findAll();
            List<Role> roles = roleService.findAll();

            model.addAttribute("cities", cities);
            model.addAttribute("roles", roles);
            return "provider-edit";
        }

        provider.setId(id);
        providerService.save(provider);
        return "redirect:";
    }

    @RequestMapping(value = "/providers/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("provider") Provider provider) {
        Role role = roleService.findByName("PROVIDER");
        provider.setRole(role);
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        provider.setStartWorkDate(new Date());
        providerService.save(provider);
        return "redirect:";
    }

}
