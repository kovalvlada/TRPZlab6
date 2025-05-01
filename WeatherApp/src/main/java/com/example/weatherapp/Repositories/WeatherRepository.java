package com.example.weatherapp.Repositories;

import com.example.weatherapp.Models.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
