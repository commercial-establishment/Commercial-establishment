package kz.hts.ce.service;

import kz.hts.ce.model.entity.Admin;
import kz.hts.ce.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AdminService extends BaseService<Admin, AdminRepository> {

    @Autowired
    protected AdminService(AdminRepository repository) {
        super(repository);
    }

    public Admin findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void updatePasswordById(String password, UUID id) {
        repository.updatePasswordById(password, id);
    }

    public List<Admin> findByRoleName(String roleName) {
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

    public Admin findByUsernameAndBlocked(String username, boolean blocked) {
        return repository.findByUsernameAndBlocked(username, blocked);
    }

    public boolean usernameExists(String username) {
        return repository.findByUsername(username) != null;
    }
}
