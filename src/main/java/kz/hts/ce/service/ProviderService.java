package kz.hts.ce.service;

import kz.hts.ce.entity.Category;
import kz.hts.ce.entity.Provider;
import kz.hts.ce.entity.Role;
import kz.hts.ce.repository.ProviderRepository;
import kz.hts.ce.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProviderService extends BaseService<Provider, ProviderRepository> {

    private static final String PROVIDER = "PROVIDER";

    @Autowired
    private CityService cityService;

    @Autowired
    protected ProviderService(ProviderRepository repository) {
        super(repository);
    }

    public Provider findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Provider findByUsernameAndBlocked(String username, boolean blocked) {
        return repository.findByUsernameAndBlocked(username, blocked);
    }

    public void updatePasswordById(String password, UUID id) {
        repository.updatePasswordById(password, id);
    }

    public List<Provider> findByRoleName(String roleName) {
        return repository.findByRole_Name(roleName);
    }

    public void lockById(UUID id) {
        repository.lockById(id);
    }

    public void reestablishById(UUID id) {
        repository.reestablishById(id);
    }

    public void updateStartAndEndWorkDate(Date startWorkDate, Date endWorkDate, UUID id) {
        repository.updateStartAndEndWorkDate(startWorkDate, endWorkDate, id);
    }

    public void updateEndWorkDate(Date endWorkDate, UUID id) {
        repository.updateEndWorkDate(endWorkDate, id);
    }


    public List<Provider> getHistory(long time) {
        List<Provider> allProviders = findAll();
        List<Provider> providers = new ArrayList<>();
        for (Provider providerFromAllProviders : allProviders) {
            Revisions<Integer, Provider> revisions = repository.findRevisions(providerFromAllProviders.getId());
            List<Revision<Integer, Provider>> revisionList = revisions.getContent();
            Provider provider = null;
            for (Revision<Integer, Provider> revision : revisionList) {
                long dateTimeInMillis = revision.getMetadata().getRevisionDate().getMillis();
                if (time < dateTimeInMillis) {
                    provider = revision.getEntity();
                    provider.setCity(providerFromAllProviders.getCity());/*FIXME*/
                    provider.setRole(SpringUtil.roleMap.get(PROVIDER));
                }
            }
            if (provider != null) providers.add(provider);
        }
        return providers;
    }
}
