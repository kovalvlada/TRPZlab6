package com.example.weatherapp.Repositories;

import com.example.weatherapp.Models.Weather;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherRepositoryTest {

    @Mock
    private WeatherRepository weatherRepository;

    private Weather weather;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create a sample Weather object for testing
        weather = new Weather(1L, "Kyiv", 15.5, "Sunny");
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        when(weatherRepository.findAll()).thenReturn(weatherList);

        // Act
        List<Weather> result = weatherRepository.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Kyiv", result.get(0).getCity());
        verify(weatherRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        // Arrange
        when(weatherRepository.findById(1L)).thenReturn(Optional.of(weather));

        // Act
        Optional<Weather> result = weatherRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Kyiv", result.get().getCity());
        verify(weatherRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        // Arrange
        when(weatherRepository.save(any(Weather.class))).thenReturn(weather);

        // Act
        Weather result = weatherRepository.save(weather);

        // Assert
        assertNotNull(result);
        assertEquals("Kyiv", result.getCity());
        verify(weatherRepository, times(1)).save(weather);
    }

    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(weatherRepository).deleteById(1L);

        // Act
        weatherRepository.deleteById(1L);

        // Assert
        verify(weatherRepository, times(1)).deleteById(1L);
    }
}
