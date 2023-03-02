package com.WeatherInformation.Information.service;


import com.WeatherInformation.Information.entity.Pincode;
import com.WeatherInformation.Information.entity.Weather;
import com.WeatherInformation.Information.entity.WeatherResponse;
import com.WeatherInformation.Information.repository.PincodeRepository;
import com.WeatherInformation.Information.repository.WeatherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class WeatherInfoService {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    PincodeRepository pincodeRepository;

    private static final String API_KEY = "b4854e6db9353c5be0dcef0f7a48eb16";

    private static final String URL = "https://api.openweathermap.org/data/2.5/weather";

    private static final String PINCODE_URL = "http://api.openweathermap.org/geo/1.0/zip";

    Logger logger = LoggerFactory.getLogger("WeatherServiceLogger");

    public Weather getWeatherDetail(String pincode_id, LocalDate date) {
        logger.info("Inside weather service");
        long utcDate = date.atStartOfDay(ZoneOffset.UTC).toEpochSecond();

        Weather weather = new Weather();

        if(findWeatherbyPincode(pincode_id).isPresent())
        {
            logger.info("Weather exist in DB");
           weather = findWeatherbyPincode(pincode_id).orElse(null);
           return weather;
        }
        else{
            logger.info("Weather does not exist in DB");
            String url = createUrl(pincode_id,utcDate);
            WeatherResponse weatherResponse = fecthWeatherData(url);
            weather.setPincode(pincode_id);
            weather.setTemp_max(weatherResponse.main.temp_max);
            weather.setTemp_min(weatherResponse.main.temp_min);
            weather.setDescription(String.valueOf(weatherResponse.weather.get(0).getDescription()));
            weatherRepository.save(weather);
            return weather;
        }

    }

    private String createUrl(String pincode_id,long utcDate ){

        if(getPincodeByCode(pincode_id).isPresent()){
            logger.info("Pincode exists in DB");
            Pincode pincode = getPincodeByCode(pincode_id).orElse(null);
            String latitude = pincode.getLat();
            String longitude = pincode.getLon();
            String url = URL +"?lat=" + latitude +"&lon=" + longitude + "&appid=" + API_KEY;
            return url;
        }
        else {
            logger.info("pincode not exist in DB");
            String pincodeUrl = PINCODE_URL+ "?zip=" + pincode_id + ",in&appid=" + API_KEY;
            Pincode pincode = fecthPincodeData(pincodeUrl);
            String latitude = pincode.getLat();
            String longitude = pincode.getLon();
            String url = URL +"?lat=" + latitude +"&lon=" + longitude +"&appid=" + API_KEY;
            return url;

        }
    }

    private Pincode fecthPincodeData(String url){
        RestTemplate restTemplate = new RestTemplate();
        Pincode pincode = new Pincode();
        pincode = restTemplate.getForObject(url, Pincode.class);
        pincodeRepository.save(pincode);
        return pincode;
    }

    private WeatherResponse fecthWeatherData(String url){
        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse weatherResponse = restTemplate.getForObject(url, WeatherResponse.class);
        return weatherResponse;
    }

    public Optional<Pincode> getPincodeByCode(String code) {
        return this.pincodeRepository.findByZip(code);

    }


    public Pincode savePincode(Pincode pincode) {
        return this.pincodeRepository.save(pincode);

}
    public Optional<Weather> findWeatherbyPincode(String pincode){
        return this.weatherRepository.findBypincode(pincode);
    }
}