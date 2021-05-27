package com.coding.zoo.zoo.controller;

import com.coding.zoo.zoo.domain.AnimalRequestPresenter;
import com.coding.zoo.zoo.domain.AnimalResponsePresenter;
import com.coding.zoo.zoo.exception.AssignmentException;
import com.coding.zoo.zoo.model.Animal;
import com.coding.zoo.zoo.repository.AnimalRepository;
import com.coding.zoo.zoo.service.AnimalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@Validated
@RequestMapping("/animal")
public class AnimalController {
    private AnimalService animalService;
    private AnimalRepository animalRepository;

    @Autowired
    public AnimalController(AnimalService animalService, AnimalRepository animalRepository) {
        this.animalService = animalService;
        this.animalRepository = animalRepository;
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public int fetchAnimalCount() {
        log.info("Fetch Animal Count");
        return animalRepository.findAll().size();
    }

    @RequestMapping(value = "/addAnimals", method = RequestMethod.POST)
    public AnimalResponsePresenter addAnimals(@RequestBody AnimalRequestPresenter animalRequestPresenter) {
        log.info("Add Animals : {}", animalRequestPresenter);
        return animalService.addAnimal(animalRequestPresenter);
    }

    @RequestMapping(value = "/removeAnimal", method = RequestMethod.DELETE)
    public String removeAnimal(@PathVariable Long animalId) {
        log.info("Animal to remove : {}", animalId);
        return animalService.removeAnimal(animalId);
    }

    @RequestMapping(value = "/assignAnimalToPen", method = RequestMethod.POST)
    public ResponseEntity assignAnimalToPen(@RequestParam Long animalId, @RequestParam Long penId) throws AssignmentException {
        log.info("Assign Animal to Pen : {}", animalId);
        return ResponseEntity.ok(animalService.assignAnimalToPen(animalId, penId));
    }

    // available pens ( Area 1, pen 1, pen 2, pen 3) Area 2 p1, p2 p3

}
