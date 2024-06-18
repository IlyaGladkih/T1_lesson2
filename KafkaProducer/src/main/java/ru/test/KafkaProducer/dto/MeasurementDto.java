package ru.test.KafkaProducer.dto;

import lombok.Data;

@Data
public class MeasurementDto {
    private final String statistic;
    private final double value;
}
