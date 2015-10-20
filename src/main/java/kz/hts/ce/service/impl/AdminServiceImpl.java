package kz.hts.ce.service.impl;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.User;
import kz.hts.ce.repository.AdminRepository;
import kz.hts.ce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public void insert(Admin admin) {
        adminRepository.save(admin);
    }

    @Transactional
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
}
