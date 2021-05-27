package com.coding.zoo.zoo.domain;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.constants.RestrictionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomAttribute implements Serializable {
    @Builder.Default
    private boolean canFly = false;
    private Map<RestrictionTypeEnum, AnimalEnum> restrictionMap;
}
