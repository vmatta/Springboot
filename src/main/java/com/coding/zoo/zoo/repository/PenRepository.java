package com.coding.zoo.zoo.repository;

import com.coding.zoo.zoo.model.Pen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PenRepository extends JpaRepository<Pen, Long> {
    @Query("Select p from Pen p where p.status=false")
    List<Pen> availablePens();

    @Modifying
    @Query("Update Pen p set p.status=true where p.id=:id")
    int updatePenStatus(Long id);
}
