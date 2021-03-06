package kz.hts.ce.repository;

import kz.hts.ce.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    Admin findByUsername(String username);

    Admin findByUsernameAndBlocked(String username, boolean blocked);

    List<Admin> findByRole_Name(String roleName);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.password = ?1 where a.id = ?2")
    void updatePasswordById(String password, UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.blocked = TRUE where a.id = ?1")
    void lockById(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.blocked = FALSE where a.id = ?1")
    void reestablishById(UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.startWorkDate = ?1, a.endWorkDate = ?2 where a.id = ?3")
    void updateStartAndEndWorkDate(Date startWorkDate, Date endWorkDate, UUID id);

    @Transactional
    @Modifying
    @Query("UPDATE Admin a set a.endWorkDate = ?1 where a.id = ?2")
    void updateEndWorkDate(Date endWorkDate, UUID id);
}
