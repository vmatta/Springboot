package com.coding.zoo.zoo.config;

import com.coding.zoo.zoo.business.AreaValidator;
import com.coding.zoo.zoo.business.PenValidator;
import com.coding.zoo.zoo.domain.UpdateAreaRequestPresenter;
import com.coding.zoo.zoo.model.Area;
import com.coding.zoo.zoo.model.Pen;
import com.coding.zoo.zoo.service.AnimalService;
import com.coding.zoo.zoo.service.AreaService;
import com.coding.zoo.zoo.service.PenService;
import com.coding.zoo.zoo.service.RestrictionValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class ZooConfig {

    @Value( "${zoo.area.pen.count}" )
    private int penCount;


    @Value( "${zoo.areas.count}" )
    private int areaCount;

    @Value("${initialize.zoo.default.data}")
    private boolean initializeZoo;

    @Autowired
    AreaService areaService;

    @Autowired
    AnimalService animalService;

    @Autowired
    PenService penService;

    private final AreaValidator areaValidator;
    private final PenValidator penValidator;

    @Autowired
    public ZooConfig(AreaValidator areaValidator, PenValidator penValidator) {
        this.areaValidator = areaValidator;
        this.penValidator = penValidator;
    }


    @Bean("restrictionValidators")
    public List<RestrictionValidator> restrictionValidators() {
        return Arrays.asList(areaValidator, penValidator);
    }

    @PostConstruct
    private void postConstruct() {
        log.info("Initialization Zoo With initial Data based on property");
        //Setup Zoo from Scratch
        //Create Area, Pens, Add few Animals
        if(initializeZoo){
            List<Area> areaList = areaService.setUpAreas(areaCount);
            List<Pen> pens = penService.addPens(penCount);
            List<String> list = new ArrayList<>();
            list.add("1");list.add("2");list.add("3");list.add("4");
            Area area = areaService.updateArea(UpdateAreaRequestPresenter.builder().areaId(1l).penIdList(list).build());
            list = new ArrayList<>();
            list.add("5");list.add("6");list.add("7");list.add("8");
            areaService.updateArea(UpdateAreaRequestPresenter.builder().areaId(2l).penIdList(list).build());
            list = new ArrayList<>();
            list.add("9");list.add("10");list.add("11");list.add("12");
            areaService.updateArea(UpdateAreaRequestPresenter.builder().areaId(3l).penIdList(list).build());
            list = new ArrayList<>();
            list.add("13");list.add("14");list.add("15");list.add("16");
            areaService.updateArea(UpdateAreaRequestPresenter.builder().areaId(4l).penIdList(list).build());
            animalService.setUpAnimals();
        }
    }
}
