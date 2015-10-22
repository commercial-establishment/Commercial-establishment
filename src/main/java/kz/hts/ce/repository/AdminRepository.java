package kz.hts.ce.repository;

import kz.hts.ce.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

    @Modifying
    @Query("UPDATE Admin a set a.password = ?1 where a.id = ?2")
    void updatePasswordById(String password, long id);

    List<Admin> findByRole_Name(String roleName);
}
