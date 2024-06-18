package ru.test.KafkaConsumer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetricDto {

    private String name;

    private String description;

    private String baseUnit;

    private double value;

    private double change;
}
