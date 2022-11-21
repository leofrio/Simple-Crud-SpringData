package com.springData.FirstSpringData.orm;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="workers")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private  Integer id;
    @Getter @Setter
    private  String  name;
    @Getter @Setter
    private  String  cpf;
    @Getter @Setter
    private  double  salary;
    @Getter @Setter
    private  Date hiring_Date;
    @Getter @Setter
    @ManyToOne
    private Job job;
    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    private List<WorkPlace> workPlaces;
}
