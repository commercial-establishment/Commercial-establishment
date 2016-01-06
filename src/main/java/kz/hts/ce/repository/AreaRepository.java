package kz.hts.ce.repository;

import kz.hts.ce.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AreaRepository extends JpaRepository<Area, UUID> {

    List<Area> findByCity_Id(UUID id);

    Area findByName(String name);
}


