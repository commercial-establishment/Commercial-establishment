package kz.hts.ce.repository;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);
}
