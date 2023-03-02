package com.WeatherInformation.Information.repository;

import com.WeatherInformation.Information.entity.Pincode;
import com.WeatherInformation.Information.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> findBypincode(String pincode);
}