package com.example.weatherapp.Controllers;

import com.example.weatherapp.DTO.WeatherDTO;
import com.example.weatherapp.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<List<WeatherDTO>> getAllWeather() {
        return ResponseEntity.ok(weatherService.getAllWeather());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherDTO> getWeatherById(@PathVariable Long id) {
        return ResponseEntity.ok(weatherService.getWeatherById(id));
    }

    @PostMapping
    public ResponseEntity<WeatherDTO> createWeather(@RequestBody WeatherDTO weatherDTO) {
        return ResponseEntity.ok(weatherService.createWeather(weatherDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WeatherDTO> updateWeather(@PathVariable Long id, @RequestBody WeatherDTO weatherDTO) {
        return ResponseEntity.ok(weatherService.updateWeather(id, weatherDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWeather(@PathVariable Long id) {
        weatherService.deleteWeather(id);
        return ResponseEntity.noContent().build();
    }
}
