package com.coding.zoo.zoo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAreaRequestPresenter {
    private Long areaId;
    private List<String> penIdList;
}
