package com.springData.FirstSpringData.orm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="work-places")
public class WorkPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String address;
}
