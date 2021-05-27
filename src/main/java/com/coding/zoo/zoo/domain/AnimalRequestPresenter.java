package com.coding.zoo.zoo.domain;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimalRequestPresenter implements Serializable {

    private String name;

    private AnimalEnum type;

    private CustomAttribute customAttribute;

    private Long penId;

    @Builder.Default
    private boolean canFly = false;

    public static Animal convert(AnimalRequestPresenter animalRequestPresenter) {
        return Animal.builder()
                .name(animalRequestPresenter.getName())
                .customAttribute(animalRequestPresenter.getCustomAttribute())
                .type(animalRequestPresenter.getType())
                .penId(animalRequestPresenter.getPenId())
                .canFly(animalRequestPresenter.isCanFly())
            .build();

    }
}
