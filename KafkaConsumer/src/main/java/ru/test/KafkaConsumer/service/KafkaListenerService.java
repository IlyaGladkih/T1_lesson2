package ru.test.KafkaConsumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.test.KafkaConsumer.converter.MetricConverter;
import ru.test.KafkaConsumer.dto.KafkaMessage;
import ru.test.KafkaConsumer.entity.Metric;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaListenerService {

    private final MetricService metricService;
    public final MetricConverter converter;

    @KafkaListener(id = "metric",topics = "metrics-topic")
    public void listen(KafkaMessage message){
        log.info("Прочитана метрика " + message.toString());
        Metric metric = this.handle(message);
        metricService.save(metric);

    }

    private Metric handle(KafkaMessage message){
        Optional<Metric> byId = metricService.findById(message.getName());
        if (byId.isPresent()){
            Metric metric = byId.get();
            double change = message.getValue() - metric.getValue();
            logChange(change, message.getName(), message.getBaseUnit());
            Metric newMetric = converter.kafkaMessageToMetric(message);
            newMetric.setChange(change);
            return newMetric;
        }else return converter.kafkaMessageToMetric(message);
    }

    private void logChange(double change, String name, String baseUnit){
        if(change >= 0) log.info("Значение метрики {} увеличилось на {} {}" , name , change, baseUnit);
        else log.info("Значение метрики {} уменьшилось на {} {}" , name , change, baseUnit);
    }
}
