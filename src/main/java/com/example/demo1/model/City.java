package com.example.demo1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3)
    private String name;

    @ManyToOne
    @JoinColumn(columnDefinition = "country_id")
    private Country country;

    @Min(1000)
    private int acreage;

    @Min(100)
    private int population;

    @Min(1)
    private int gdp;

    @Size(min = 20)
    private String introduce;

    public City() {
    }
}
