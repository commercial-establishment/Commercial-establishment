package kz.hts.ce.repository;

import kz.hts.ce.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findByCity_Id(long id);

    Area findByName(String name);
}


