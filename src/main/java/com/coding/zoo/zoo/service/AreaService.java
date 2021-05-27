package com.coding.zoo.zoo.service;

import com.coding.zoo.zoo.constants.Constants;
import com.coding.zoo.zoo.domain.AreaPresenter;
import com.coding.zoo.zoo.domain.UpdateAreaRequestPresenter;
import com.coding.zoo.zoo.model.Area;
import com.coding.zoo.zoo.repository.AreaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class AreaService {


    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    PenService penService;

    public Area addAreaToZoo(AreaPresenter areaPresenter) {
        log.info("Add Area to Zoo");
        Area area = AreaPresenter.toEntity(areaPresenter);
        return areaRepository.save(area);
    }

    @Transactional
    public Area updateArea(UpdateAreaRequestPresenter updateAreaRequestPresenter) {
        log.info("Update Area with Pen Ids {} ", updateAreaRequestPresenter);
        Area area = areaRepository.findById(updateAreaRequestPresenter.getAreaId()).get();
        area.setPenList(updateAreaRequestPresenter.getPenIdList());
        penService.updatePenWithAreaDetails(area);
        return areaRepository.save(area);
    }

    public List<Area> setUpAreas(int areaCount) {
        List<Integer> list = Collections.nCopies(areaCount, areaCount);
        AtomicInteger atomicInteger = new AtomicInteger(1);

        List<Area> areaList = list.stream()
                .map(l -> Area.builder()
                        .name(Constants.DEFAULT_AREA_NAME.concat(String.valueOf(atomicInteger.getAndIncrement())))
                    .build())
                .collect(toList());
        return areaRepository.saveAll(areaList);
    }

    public List<String> fetchAllPenIdsInArea(Long areaId) {
        log.info("Fetch All Pen Ids in Area with area id {}", areaId);
        return areaRepository.findById(areaId).get().getPenList();
    }

    public List<Area> fetchAllAreas() {
        log.info("Fetch All Areas");
        return areaRepository.findAll();
    }
}
