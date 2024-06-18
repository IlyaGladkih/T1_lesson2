package ru.test.KafkaProducer.dto;

import lombok.Data;

import java.util.List;

@Data
public class MetricDto {
    private String name;
    private String description;
    private String baseUnit;
    private List<MeasurementDto> measurements;
}
