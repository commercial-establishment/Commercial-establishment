package kz.hts.ce.service.impl;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.repository.AdminRepository;
import kz.hts.ce.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseService<Admin, AdminRepository> {

    @Autowired
    protected AdminServiceImpl(AdminRepository repository) {
        super(repository);
    }
}
