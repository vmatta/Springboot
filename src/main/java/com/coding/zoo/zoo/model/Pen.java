package com.coding.zoo.zoo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Pen")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pen implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status;

    private String areaName;

    private Long areaId;
}
