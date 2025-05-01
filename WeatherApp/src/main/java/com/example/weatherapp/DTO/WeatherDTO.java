package com.example.weatherapp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherDTO {
    private Long id;
    private String city;
    private Double temperature;
    private String description;
}
