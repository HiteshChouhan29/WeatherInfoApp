package com.WeatherInformation.Information.entity;


import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "pincode")
public class Pincode{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "latitude", nullable = false)
    private String lat;

    @Column(name = "longitude", nullable = false)
    private String lon;

    @Column(name = "country", nullable = false)
    private String country;

}
