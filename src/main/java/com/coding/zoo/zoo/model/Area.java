package com.coding.zoo.zoo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "area")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Area implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PEN_CODES")
    @Column(name = "penList")
    private List<String> penList = new ArrayList();

}
