package com.multidbdemo.app.config.primary;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="st_name")
    private String name;
    @Column(name="st_roll")
    private Integer rollNo;

}
