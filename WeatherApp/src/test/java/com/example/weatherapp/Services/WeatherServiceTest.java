package com.example.weatherapp.Services;

import com.example.weatherapp.DTO.WeatherDTO;
import com.example.weatherapp.Models.Weather;
import com.example.weatherapp.Repositories.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllWeather() {
        Weather weather = new Weather(1L, "Kyiv", 15.5, "Sunny");
        when(weatherRepository.findAll()).thenReturn(List.of(weather));

        List<WeatherDTO> weatherDTOs = weatherService.getAllWeather();

        assertEquals(1, weatherDTOs.size());
        assertEquals("Kyiv", weatherDTOs.get(0).getCity());
        verify(weatherRepository, times(1)).findAll();
    }

    @Test
    void testGetWeatherById() {
        Weather weather = new Weather(1L, "Kyiv", 15.5, "Sunny");
        when(weatherRepository.findById(1L)).thenReturn(Optional.of(weather));

        WeatherDTO weatherDTO = weatherService.getWeatherById(1L);

        assertEquals("Kyiv", weatherDTO.getCity());
        verify(weatherRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateWeather() {
        Weather weather = new Weather(1L, "Kyiv", 15.5, "Sunny");
        WeatherDTO weatherDTO = new WeatherDTO(null, "Kyiv", 15.5, "Sunny");
        when(weatherRepository.save(any(Weather.class))).thenReturn(weather);

        WeatherDTO createdWeather = weatherService.createWeather(weatherDTO);

        assertEquals("Kyiv", createdWeather.getCity());
        verify(weatherRepository, times(1)).save(any(Weather.class));
    }

    @Test
    void testUpdateWeather() {
        Weather existingWeather = new Weather(1L, "Kyiv", 15.5, "Sunny");
        Weather updatedWeather = new Weather(1L, "Lviv", 10.0, "Cloudy");
        WeatherDTO weatherDTO = new WeatherDTO(null, "Lviv", 10.0, "Cloudy");

        when(weatherRepository.findById(1L)).thenReturn(Optional.of(existingWeather));
        when(weatherRepository.save(existingWeather)).thenReturn(updatedWeather);

        WeatherDTO updatedWeatherDTO = weatherService.updateWeather(1L, weatherDTO);

        assertEquals("Lviv", updatedWeatherDTO.getCity());
        verify(weatherRepository, times(1)).findById(1L);
        verify(weatherRepository, times(1)).save(existingWeather);
    }

    @Test
    void testDeleteWeather() {
        doNothing().when(weatherRepository).deleteById(1L);

        weatherService.deleteWeather(1L);

        verify(weatherRepository, times(1)).deleteById(1L);
    }
}
