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

    //
//    @RequestMapping(value = "/replication/providers/add", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//    @ResponseBody
//    public List<Provider> getProvidersFromClient(@RequestBody List<Provider> providers) {
//        for (Provider provider : providers) providerService.save(provider);
//        return providers;
//    }
//
//    @RequestMapping(value = "/replication/providers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    @ResponseBody
//    public List<Provider> sendAllProvidersToClient() {
//        return providerService.findAll();
//    }

    @RequestMapping(value = "/replication/providers/time={time}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public List<Provider> sendNewProvidersDataToClient(@PathVariable long time) {
        return providerService.getHistory(time);
    }

    @RequestMapping(value = "/replication/providers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public void saveProvidersChangesFromClient(@RequestBody List<Provider> providers) {
        for (Provider provider : providers) providerService.save(provider);
    }
}
