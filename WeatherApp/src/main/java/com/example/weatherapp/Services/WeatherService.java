package com.example.weatherapp.Services;

import com.example.weatherapp.DTO.WeatherDTO;
import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    public List<WeatherDTO> getAllWeather() {
        return weatherRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public WeatherDTO getWeatherById(Long id) {
        return weatherRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Weather not found"));
    }

    public WeatherDTO createWeather(WeatherDTO weatherDTO) {
        Weather weather = convertToEntity(weatherDTO);
        return convertToDTO(weatherRepository.save(weather));
    }

    public WeatherDTO updateWeather(Long id, WeatherDTO weatherDTO) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Weather not found"));
        weather.setCity(weatherDTO.getCity());
        weather.setTemperature(weatherDTO.getTemperature());
        weather.setDescription(weatherDTO.getDescription());
        return convertToDTO(weatherRepository.save(weather));
    }

    public void deleteWeather(Long id) {
        weatherRepository.deleteById(id);
    }

    private WeatherDTO convertToDTO(Weather weather) {
        return WeatherDTO.builder()
                .id(weather.getId())
                .city(weather.getCity())
                .temperature(weather.getTemperature())
                .description(weather.getDescription())
                .build();
    }

    private Weather convertToEntity(WeatherDTO weatherDTO) {
        return Weather.builder()
                .id(weatherDTO.getId())
                .city(weatherDTO.getCity())
                .temperature(weatherDTO.getTemperature())
                .description(weatherDTO.getDescription())
                .build();
    }
}
