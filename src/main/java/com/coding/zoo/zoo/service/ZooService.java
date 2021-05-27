package com.coding.zoo.zoo.service;

import com.coding.zoo.zoo.domain.AnimalResponsePresenter;
import com.coding.zoo.zoo.domain.AreaPresenter;
import com.coding.zoo.zoo.model.Area;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ZooService {

    private AnimalService animalService;
    private AreaService areaService;

    @Autowired
    public ZooService(AreaService areaService, AnimalService animalService) {
        this.areaService = areaService;
        this.animalService = animalService;
    }

    public Area addArea(AreaPresenter areaPresenter) {
        log.info("Add area to Zoo for {}", areaPresenter);
        return areaService.addAreaToZoo(areaPresenter);
    }

    public List<AnimalResponsePresenter> fetchAllAnimals() {
        return animalService.fetchAllAnimals();
    }
}
