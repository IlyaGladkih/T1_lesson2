package ru.test.KafkaConsumer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.test.KafkaConsumer.dto.MetricDto;
import ru.test.KafkaConsumer.entity.Metric;
import ru.test.KafkaConsumer.service.MetricService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/metrics/")
public class MetricController {

    private final MetricService service;

    @GetMapping()
    public ResponseEntity<List<MetricDto>> getAllMetric(){
        List<MetricDto> allMetric = service.findAllMetric();
        if(allMetric.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(allMetric);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricDto> getAllMetric(@PathVariable String id){
        MetricDto metric = service.findMetricById(id);
        if(metric == null) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(metric);
    }
}
