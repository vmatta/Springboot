package com.coding.zoo.zoo.repository;

import com.coding.zoo.zoo.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AreaRepository extends JpaRepository<Area, Long> {
    @Query("Select a from Area a where a.penList in (:penId)")
    Area findByPenList(Long penId);
}
