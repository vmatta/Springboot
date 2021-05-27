package com.coding.zoo.zoo.model;

import com.coding.zoo.zoo.constants.AnimalEnum;
import com.coding.zoo.zoo.constants.RestrictionTypeEnum;
import com.coding.zoo.zoo.domain.CustomAttribute;
import lombok.*;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "Animals")
@Getter
@Setter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;

     private AnimalEnum type;

     private String areaName;

     private Long areaId;

     private Long penId;

     private boolean penStatus;

     private CustomAttribute customAttribute;

     @Builder.Default
     private boolean canFly = false;
}
