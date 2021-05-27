package com.coding.zoo.zoo.service;

import com.coding.zoo.zoo.exception.AssignmentException;
import com.coding.zoo.zoo.model.Animal;

import java.util.List;


public interface RestrictionValidator {
    void validate(List<Animal> animalList, Animal animal, int startingPenId, int endingPenId, Long assigningPenId) throws AssignmentException;
}
