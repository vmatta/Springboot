package com.coding.zoo.zoo.service;

import com.coding.zoo.zoo.model.Area;
import com.coding.zoo.zoo.model.Pen;
import com.coding.zoo.zoo.repository.PenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class PenService {

    @Autowired
    private PenRepository penRepository;

    public List<Pen> addPens(int penCount) {
        log.info("Add pens");
        List<Integer> list = Collections.nCopies(penCount, penCount);

        List<Pen> penList = list.stream()
                .map(l -> Pen.builder().status(false).build())
                .collect(toList());

        return penRepository.saveAll(penList);
    }

    public List<Pen> availablePens() {
        log.info("Fetch Available Pens");
        return penRepository.availablePens();
    }
    @Transactional
    public int updatePenStatus(Long penId) {
        log.info("Update Pen Status");
        return penRepository.updatePenStatus(penId);
    }

    public List<Pen> updatePenWithAreaDetails(Area area) {
        log.info("Update Pen with Area Details");
        List<Pen> penList = area.getPenList().stream()
                .map(penId -> Pen.builder()
                        .id(Long.valueOf(penId))
                        .areaName(area.getName())
                        .areaId(area.getId())
                        .build())
                .collect(toList());
        return penRepository.saveAll(penList);
    }

    public Pen getPenById(Long penId) {
        log.info("");
        return penRepository.findById(penId).get();
    }
}
