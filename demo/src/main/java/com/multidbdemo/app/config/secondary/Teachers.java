package com.multidbdemo.app.config.secondary;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "teachers")
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="t_name")
    private String name;
    @Column(name="t_sub")
    private String subject;

}