package com.ships.prueba.infrastructure.repository;

import com.ships.prueba.domain.entity.model.Ship;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipsRepository extends JpaRepository<Ship, Long> {

    @Query("SELECT s FROM Ship s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Ship> findByName(@Param("name") String name);
}
