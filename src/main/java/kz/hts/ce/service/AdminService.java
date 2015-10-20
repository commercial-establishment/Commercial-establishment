package kz.hts.ce.service;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.repository.AdminRepository;
import kz.hts.ce.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends BaseService<Admin, AdminRepository> {

    @Autowired
    protected AdminService(AdminRepository repository) {
        super(repository);
    }
}
