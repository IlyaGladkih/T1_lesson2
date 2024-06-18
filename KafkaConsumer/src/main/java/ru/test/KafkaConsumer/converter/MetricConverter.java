package ru.test.KafkaConsumer.converter;

import org.springframework.stereotype.Component;
import ru.test.KafkaConsumer.dto.KafkaMessage;
import ru.test.KafkaConsumer.dto.MetricDto;
import ru.test.KafkaConsumer.entity.Metric;

@Component
public class MetricConverter {

    public Metric kafkaMessageToMetric(KafkaMessage message){
        return Metric.builder()
                .name(message.getName())
                .description(message.getDescription())
                .baseUnit(message.getBaseUnit())
                .value(message.getValue())
                .build();
    }

    public MetricDto metricToMetricDto(Metric metric){
        return MetricDto.builder()
                .name(metric.getName())
                .description(metric.getDescription())
                .baseUnit(metric.getBaseUnit())
                .value(metric.getValue())
                .change(metric.getChange())
                .build();
    }
}
