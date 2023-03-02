package com.WeatherInformation.Information.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;








@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "weather")
public class Weather {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pincode", nullable = false)
    private String pincode;

    @Column(name = "description",nullable = false)
    public String description;

    @Column(name = "temp_min", nullable = false)
    private Double temp_min;

    @Column(name = "temp_max", nullable = false)
    private Double temp_max;


}