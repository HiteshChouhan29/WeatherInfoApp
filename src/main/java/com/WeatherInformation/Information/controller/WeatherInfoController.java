package com.WeatherInformation.Information.controller;

import com.WeatherInformation.Information.entity.Pincode;
import com.WeatherInformation.Information.entity.Weather;
import com.WeatherInformation.Information.repository.WeatherRepository;
import com.WeatherInformation.Information.service.WeatherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class WeatherInfoController {

    @Autowired
    WeatherInfoService weatherInfoService;

    @GetMapping("/get")
    public Weather getWeatherInfo(@RequestParam String pincode_id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate for_date) {

        return weatherInfoService.getWeatherDetail(pincode_id, for_date);

    }

}