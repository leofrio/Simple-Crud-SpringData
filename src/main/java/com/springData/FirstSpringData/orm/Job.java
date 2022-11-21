package com.springData.FirstSpringData.orm;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Column(unique = true)
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String description;
    @OneToMany(mappedBy = "job")
    private List<Worker> workers;
}
