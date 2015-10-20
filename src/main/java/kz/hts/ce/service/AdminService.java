package kz.hts.ce.service;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.User;

import java.util.List;

public interface AdminService {

    void insert(Admin admin);

    List<Admin> findAll();
}
