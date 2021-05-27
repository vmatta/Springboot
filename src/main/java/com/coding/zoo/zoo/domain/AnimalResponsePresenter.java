package com.coding.zoo.zoo.domain;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponsePresenter {
    private Long id;

    private String name;

    private AnimalEnum type;

    private String areaName;

    private Long areaId;

    private Long penId;

    private CustomAttribute customAttribute;

    @Builder.Default
    private boolean canFly = false;

    public static AnimalResponsePresenter fromEntity(Animal animal) {
        return AnimalResponsePresenter.builder()
                .id(animal.getId())
                .name(animal.getName())
                .type(animal.getType())
                .customAttribute(animal.getCustomAttribute())
                .areaId(animal.getAreaId())
                .areaName(animal.getAreaName())
                .penId(Optional.ofNullable(animal.getPenId()).orElse(null))
                .canFly(Optional.ofNullable(animal.isCanFly()).orElse(false))
            .build();
    }
}
