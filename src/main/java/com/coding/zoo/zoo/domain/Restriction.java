package com.coding.zoo.zoo.domain;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.constants.RestrictionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restriction implements Serializable {
    private RestrictionTypeEnum restrictionTypeEnum;
    private AnimalEnum restrictedAnimalType;
}
