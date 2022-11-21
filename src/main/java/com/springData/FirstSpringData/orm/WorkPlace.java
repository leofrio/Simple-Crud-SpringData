package com.springData.FirstSpringData.orm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="work_places")
public class WorkPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String address;
    @ManyToMany(mappedBy = "workPlaces",fetch = FetchType.EAGER)
    private List<Worker> workers;
}
