package kz.hts.ce.repository;

import kz.hts.ce.entity.Admin;
import kz.hts.ce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

    @Modifying
    @Query("UPDATE User u set u.password = ?1 where u.id = ?2")
    void updatePasswordById(String password, long id);
}
