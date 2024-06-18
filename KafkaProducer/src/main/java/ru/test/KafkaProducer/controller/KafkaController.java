package ru.test.KafkaProducer.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.KafkaProducer.converter.DtoMessageConverter;
import ru.test.KafkaProducer.dto.KafkaMessage;
import ru.test.KafkaProducer.dto.MetricDto;

@RestController
@RequestMapping("api/v1/")
@Slf4j
@RequiredArgsConstructor
public class KafkaController {

    private final KafkaTemplate<Object, Object> template;

    private final DtoMessageConverter converter;

    @PostMapping("metrics/")
    public ResponseEntity<?> reciveMetrics(@RequestBody MetricDto metric){
        KafkaMessage message = converter.toMessage(metric);
        log.info("Получена метрика {}", message.toString());
        template.send("metrics-topic",message);
        return ResponseEntity.accepted().build();
    }
}
