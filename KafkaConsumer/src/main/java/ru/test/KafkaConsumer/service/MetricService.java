package ru.test.KafkaConsumer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.test.KafkaConsumer.converter.MetricConverter;
import ru.test.KafkaConsumer.dto.MetricDto;
import ru.test.KafkaConsumer.entity.Metric;
import ru.test.KafkaConsumer.repository.MetricRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MetricService {

    private final MetricRepository repository;

    private final MetricConverter converter;

    @Transactional
    public void save(Metric metric){
        repository.save(metric);
    }


    @Transactional
    public Optional<Metric> findById(String id){
        return repository.findById(id);
    }

    @Transactional
    public List<MetricDto> findAllMetric(){
        return this.toDto(repository.findAll());
    }

    @Transactional
    public MetricDto findMetricById(String id){
        Optional<Metric> byId = repository.findById(id);
        if(byId.isEmpty()) return null;
        else return converter.metricToMetricDto(byId.get());
    }

    private List<MetricDto> toDto(List<Metric> data){
        if(data.isEmpty()) return Collections.EMPTY_LIST;
        else return data.stream().map(converter::metricToMetricDto).collect(Collectors.toList());
    }

}
