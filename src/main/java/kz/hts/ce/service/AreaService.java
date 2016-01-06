package kz.hts.ce.service;

import kz.hts.ce.entity.Area;
import kz.hts.ce.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AreaService extends BaseService<Area, AreaRepository> {

    @Autowired
    protected AreaService(AreaRepository repository) {
        super(repository);
    }

    public List<Area> findByCityId(UUID id) {
        return repository.findByCity_Id(id);
    }

    public Area findByName(String name) {
        return repository.findByName(name);
    }
}
