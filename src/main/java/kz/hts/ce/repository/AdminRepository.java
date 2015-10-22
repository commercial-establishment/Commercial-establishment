package kz.hts.ce.repository;

import kz.hts.ce.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

    List<Admin> findByRole_Name(String roleName);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.password = ?1 where a.id = ?2")
    void updatePasswordById(String password, long id);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.blocked = TRUE where a.id = ?1")
    void lockById(long id);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.blocked = FALSE where a.id = ?1")
    void reestablishById(long id);

}
