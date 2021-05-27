package com.coding.zoo.zoo.domain;

import com.coding.zoo.zoo.model.Area;
import com.coding.zoo.zoo.model.Pen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaPresenter {
    private String areaName;

    @Builder.Default
    private List<String> penList = new ArrayList<>();

    public static Area toEntity(AreaPresenter areaPresenter) {
        return Area.builder()
                .name(areaPresenter.getAreaName())
                .penList(areaPresenter.getPenList())
            .build();

    }
}
