package com.coding.zoo.zoo.repository;

import com.coding.zoo.zoo.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    @Query("Select a from Animal a where a.areaId=:areaId")
    List<Animal> findByAreaId(Long areaId);
}
