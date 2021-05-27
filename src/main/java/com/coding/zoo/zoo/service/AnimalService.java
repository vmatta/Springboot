package com.coding.zoo.zoo.service;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.constants.RestrictionTypeEnum;
import com.coding.zoo.zoo.domain.AnimalRequestPresenter;
import com.coding.zoo.zoo.domain.AnimalResponsePresenter;
import com.coding.zoo.zoo.domain.CustomAttribute;
import com.coding.zoo.zoo.exception.AssignmentException;
import com.coding.zoo.zoo.model.Animal;
import com.coding.zoo.zoo.model.Pen;
import com.coding.zoo.zoo.repository.AnimalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.*;

@Slf4j
@Service
public class AnimalService {

    private List<RestrictionValidator> restrictionValidators;

    AnimalRepository animalRepository;

    PenService penService;

    AreaService areaService;

    @Autowired
    public AnimalService(@Qualifier("restrictionValidators") List<RestrictionValidator> restrictionValidators,
                         AnimalRepository animalRepository,
                         PenService penService,
                         AreaService areaService) {
        this.restrictionValidators = restrictionValidators;
        this.animalRepository = animalRepository;
        this.penService = penService;
        this.areaService = areaService;
    }

    public AnimalResponsePresenter addAnimal(AnimalRequestPresenter animalRequestPresenter) {
        log.info("Add animal in the zoo");
        Animal savedAnimal = animalRepository.save(AnimalRequestPresenter.convert(animalRequestPresenter));
        log.info("Assign new added animal to provided pen id");
        Optional.ofNullable(animalRequestPresenter.getPenId())
                .map(pendId -> assignAnimalToPen(savedAnimal.getId(), pendId))
                .orElse(null);
        return AnimalResponsePresenter.fromEntity(savedAnimal);
    }

    public List<AnimalResponsePresenter> addAllAnimals(List<AnimalRequestPresenter> animalRequestPresenters) {
        log.info("Add animal in the zoo");
        return animalRequestPresenters.stream()
                .map(animalRequestPresenter -> {
                    Animal savedAnimal = animalRepository.save(AnimalRequestPresenter.convert(animalRequestPresenter));
                    log.info("Assign new added animal to provided pen id");
                    Optional.ofNullable(animalRequestPresenter.getPenId())
                            .map(pendId -> assignAnimalToPen(savedAnimal.getId(), pendId))
                            .orElse(null);
                    return AnimalResponsePresenter.fromEntity(savedAnimal);
                }).collect(Collectors.toList());
    }

    public String removeAnimal(Long animalId) {
        return animalRepository.findById(animalId).isPresent() ? "Animal removed from the Zoo " : "No animal found";
    }

    public List<AnimalResponsePresenter> fetchAllAnimals() {
        List<Animal> animalsInZoo = animalRepository.findAll();
        return animalsInZoo.stream()
                .map(AnimalResponsePresenter::fromEntity)
                .collect(Collectors.toList());
    }

    public Animal assignAnimalToPen(Long animalId, Long penId) throws AssignmentException {
        Pen pen = penService.getPenById(penId);
        Animal animal = animalRepository.findById(animalId).get();
        List<String> penList = areaService.fetchAllPenIdsInArea(pen.getAreaId());
        int size = penList.size();
        sort(penList);

        List<Animal> animalsByAreaId = animalRepository.findByAreaId(pen.getAreaId());

        if(!ObjectUtils.isEmpty(animalsByAreaId)) {
            validate(animalsByAreaId, animal, Integer.parseInt(penList.get(0)) , Integer.parseInt(penList.get(size-1)), pen.getId());
        }
        updateAnimalWithAreaAndPenDetails(penId, pen.getAreaId(), pen.getAreaName(), animal);
        penService.updatePenStatus(penId);
        animal.setPenStatus(true);
        return animalRepository.save(animal);

    }

    private void validate(List<Animal> animalList, Animal assigningAnimal, Integer firstPenId, Integer lastPenId, Long assigningPenId) throws AssignmentException {
        for (RestrictionValidator restrictionValidator : restrictionValidators) {
            restrictionValidator.validate(animalList, assigningAnimal, firstPenId, lastPenId, assigningPenId);
        }
    }


    private void updateAnimalWithAreaAndPenDetails(Long penId, Long areaId, String areaName, Animal animal) {
        animal.setAreaId(areaId);
        animal.setPenId(penId);
        animal.setAreaName(areaName);
    }

    // Default method to setup initial data
    public void setUpAnimals() {
        //Mammals
        AnimalRequestPresenter lion = AnimalRequestPresenter.builder().name("Lion").type(AnimalEnum.Mammals).penId(1l).build();
        AnimalRequestPresenter tiger = AnimalRequestPresenter.builder().name("Tiger").type(AnimalEnum.Mammals).penId(2l).build();

        Map<RestrictionTypeEnum, AnimalEnum> areaRestrictMap = new HashMap<>();

        areaRestrictMap.put(RestrictionTypeEnum.Pen,AnimalEnum.Birds);

        AnimalRequestPresenter bears = AnimalRequestPresenter.builder().name("Bears").type(AnimalEnum.Mammals).penId(3l).build();

        //Reptiles
        areaRestrictMap = new HashMap<>();
        areaRestrictMap.put(RestrictionTypeEnum.Area,AnimalEnum.Birds);
        AnimalRequestPresenter snakes = AnimalRequestPresenter.builder().name("Snakes").type(AnimalEnum.Reptiles)
                .penId(4l).build();

        //Birds
        areaRestrictMap = new HashMap<>();
        areaRestrictMap.put(RestrictionTypeEnum.Area,AnimalEnum.Birds);
        areaRestrictMap.put(RestrictionTypeEnum.Pen,AnimalEnum.Birds);

        AnimalRequestPresenter Ostriches = AnimalRequestPresenter.builder().name("Ostriches").type(AnimalEnum.Birds)
                        .canFly(false).penId(5l).build();

        AnimalRequestPresenter Eagles = AnimalRequestPresenter.builder().name("Eagles").type(AnimalEnum.Birds)
                        .canFly(true).penId(6l).build();

        List<AnimalRequestPresenter> animalList = Arrays.asList(lion, tiger, bears, snakes, Ostriches, Eagles);

        addAllAnimals(animalList);
    }

}
