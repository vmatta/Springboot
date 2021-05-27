package com.coding.zoo.zoo.business;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.exception.AssignmentException;
import com.coding.zoo.zoo.model.Animal;
import com.coding.zoo.zoo.service.RestrictionValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AreaValidator implements RestrictionValidator {

    String errorMessage = "Reptiles and Birds can not be in same area";

    @Override
    public void validate(List<Animal> animalList, Animal animal, int startingPenId, int endingPenId, Long assigningPenId) throws AssignmentException {

        Set<AnimalEnum> uniqueAnimalTypes = fetchDistinctAnimalTypes(animalList);

        if(uniqueAnimalTypes.contains(AnimalEnum.Reptiles) && animal.getType().equals(AnimalEnum.Birds)){
            throw new AssignmentException(errorMessage);
        }

        if(uniqueAnimalTypes.contains(AnimalEnum.Birds) && animal.getType().equals(AnimalEnum.Reptiles)){
            throw new AssignmentException(errorMessage);
        }
    }

    private Set<AnimalEnum> fetchDistinctAnimalTypes(List<Animal> animalList) {
        return animalList
                .stream()
                .map(Animal::getType)
            .collect(Collectors.toSet());
    }
}
