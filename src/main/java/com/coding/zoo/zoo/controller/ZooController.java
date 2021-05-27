package com.coding.zoo.zoo.controller;

import com.coding.zoo.zoo.domain.AnimalResponsePresenter;
import com.coding.zoo.zoo.domain.AreaPresenter;
import com.coding.zoo.zoo.domain.UpdateAreaRequestPresenter;
import com.coding.zoo.zoo.model.Area;
import com.coding.zoo.zoo.model.Pen;
import com.coding.zoo.zoo.service.AreaService;
import com.coding.zoo.zoo.service.PenService;
import com.coding.zoo.zoo.service.ZooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Validated
@RequestMapping("/zoo")
public class ZooController {
    @Autowired
    private ZooService zooService;

    @Autowired
    private AreaService areaService;

    @Autowired
    private PenService penService;

    @RequestMapping(value = "/addArea", method = RequestMethod.POST)
    public Area addArea(@RequestBody AreaPresenter areaPresenter) {
        log.info("Add Area : {}", areaPresenter);
        return zooService.addArea(areaPresenter);
    }

    @RequestMapping(value = "/addPens", method = RequestMethod.POST)
    public List<Pen> addPen(@RequestParam int penCount) {
        log.info("Add Pen : {}", penCount);
        return penService.addPens(penCount);
    }

    @RequestMapping(value = "/updateArea", method = RequestMethod.PATCH)
    public Area updateArea(@RequestBody UpdateAreaRequestPresenter updateAreaRequestPresenter) {
        log.info("Update Area with Pen : {}", updateAreaRequestPresenter);
        return areaService.updateArea(updateAreaRequestPresenter);
    }

    @RequestMapping(value = "/fetchAllAnimals", method = RequestMethod.GET)
    public List<AnimalResponsePresenter> showAllAnimalsInZoo() {
        log.info("Fetch All Animals in Zoo");
        return zooService.fetchAllAnimals();
    }

    @RequestMapping(value = "/availablePens", method = RequestMethod.GET)
    public List<Pen> availablePens() {
        return penService.availablePens();
    }

    @RequestMapping(value = "/fetchAllAreas", method = RequestMethod.GET)
    public List<Area> fetchAllAreas() {
        return areaService.fetchAllAreas();
    }

}
