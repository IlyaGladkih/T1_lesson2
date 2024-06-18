package ru.test.KafkaProducer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaMessage {

    private String name;
    private String description;
    private String baseUnit;
    private double value;
}
