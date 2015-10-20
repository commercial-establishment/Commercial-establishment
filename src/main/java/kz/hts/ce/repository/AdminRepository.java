package kz.hts.ce.repository;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    User findbyName(String name);
}
