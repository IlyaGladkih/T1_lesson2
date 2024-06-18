package ru.test.KafkaProducer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private String errorMessage;
}
