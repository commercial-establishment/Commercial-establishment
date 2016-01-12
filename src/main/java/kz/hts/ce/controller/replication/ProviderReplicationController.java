package kz.hts.ce.controller.replication;

import kz.hts.ce.model.entity.Provider;
import kz.hts.ce.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProviderReplicationController {

    @Autowired
    private ProviderService providerService;

    @RequestMapping(value = "/replication/providers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void saveNewProvidersDataFromClient(@RequestBody List<Provider> providers) {
        for (Provider provider : providers) providerService.save(provider);
    }

    @RequestMapping(value = "/replication/providers/time={time}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Provider> sendNewProvidersDataToClient(@PathVariable long time) {
        List<Provider> providers;
        if (time == 0) providers = providerService.findAll();
        else providers = providerService.getHistory(time);
        return providers;
    }
}
