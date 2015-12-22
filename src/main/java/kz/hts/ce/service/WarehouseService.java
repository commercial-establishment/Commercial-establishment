package kz.hts.ce.service;

import kz.hts.ce.entity.Warehouse;
import kz.hts.ce.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService extends BaseService<Warehouse, WarehouseRepository>{

    @Autowired
    protected WarehouseService(WarehouseRepository repository) {
        super(repository);
    }
}
