package com.WeatherInformation.Information.repository;

import com.WeatherInformation.Information.entity.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PincodeRepository extends JpaRepository<Pincode,Long>   {
    Optional<Pincode> findByZip(String zip);
}
