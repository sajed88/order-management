package com.example.assignment2.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//import lombok.Getter;
//import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 60)
    private String name;
}
