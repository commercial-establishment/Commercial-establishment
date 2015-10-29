package kz.hts.ce.controller;


import kz.hts.ce.entity.Provider;
import kz.hts.ce.entity.Role;
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

@Controller
public class ProviderController {

    public static final String PROVIDER = "PROVIDER";

    @Autowired
    private ProviderService providerService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/providers/{id}/lock")
    public String lock(@PathVariable long id){
        Provider provider = providerService.findById(id);
        provider.setEndWorkDate(new Date());
        provider.setBlocked(true);
        providerService.save(provider);
        providerService.updateStartAndEndWorkDate(new Date(), null, id);
        providerService.lockById(id);
        return "redirect:";
    }

    @RequestMapping("/providers/{id}/reestablish")
    public String reestablish(@PathVariable long id){
        providerService.updateStartAndEndWorkDate(new Date(), null, id);
        providerService.reestablishById(id);
        return "redirect:";
    }

    @RequestMapping(value = "/providers/{id}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable long id, @Valid @ModelAttribute("provider") Provider provider, BindingResult result){
        if(result.hasErrors()){
            return "provider-edit";
        }
        Role role = roleService.findByName(PROVIDER);
        provider.setId(id);
        provider.setRole(role);
        providerService.save(provider);
        return "redirect:";
    }

    @RequestMapping(value = "/providers/create-save", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("provider") Provider provider){
        Role role = roleService.findByName("PROVIDER");
        provider.setRole(role);
        provider.setPassword(passwordEncoder.encode(provider.getPassword()));
        provider.setStartWorkDate(new Date());
        providerService.save(provider);
        return "redirect:";
    }

}
