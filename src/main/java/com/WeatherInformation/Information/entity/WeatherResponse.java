package com.WeatherInformation.Information.entity;

import lombok.*;

import java.util.ArrayList;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
    public class WeatherResponse {
       public Coord coord;

        public ArrayList<Weather> weather;

        public String base;

        public Main main;

        public int visibility;

        public Wind wind;

        public Clouds clouds;

        public int dt;

        public Sys sys;

        public int timezone;

        public String name;

        public int code;


        }

