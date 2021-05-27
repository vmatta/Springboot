package com.coding.zoo.zoo.business;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.constants.Constants;
import com.coding.zoo.zoo.exception.AssignmentException;
import com.coding.zoo.zoo.model.Animal;
import com.coding.zoo.zoo.service.RestrictionValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class PenValidator implements RestrictionValidator {
    String errorMessage = "Birds and Bears can not be in adjacent pens";
    @Override
    public void validate(List<Animal> animalList, Animal animal, int startingPenId, int endingPenId, Long assigningPenId) throws AssignmentException {
        Map<Long, Animal> filteredAnimalMap = animalList
                .stream()
                .filter(existingAnimal -> Constants.BEARS.contains(existingAnimal.getName()) || AnimalEnum.Birds.equals(existingAnimal.getType()))
                .filter(existingAnimal -> Constants.BEARS.contains(animal.getName()) || AnimalEnum.Birds.equals(animal.getType()))
                .collect(Collectors.toMap(Animal::getPenId, Function.identity()));

        Optional<Animal> adjacentAnimal = checkAdjacency(filteredAnimalMap, assigningPenId, startingPenId, endingPenId);
        if(adjacentAnimal.isPresent()){
            throw new AssignmentException(errorMessage);
        }
    }

    private Optional<Animal> checkAdjacency(Map<Long, Animal> filteredAnimalMap, Long assigningPenId, long startingPenId, long endingPenId) {
        return filteredAnimalMap.values()
                .stream()
                .filter(filteredAnimal -> filteredAnimal.getPenId().equals(assigningPenId == endingPenId ? startingPenId : assigningPenId + 1))
                .filter(filteredAnimal -> filteredAnimal.getPenId().equals(assigningPenId == startingPenId ? endingPenId : assigningPenId - 1))
            .findFirst();

    }
}
