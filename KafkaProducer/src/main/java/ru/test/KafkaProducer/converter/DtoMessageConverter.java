package ru.test.KafkaProducer.converter;

import org.springframework.stereotype.Component;
import ru.test.KafkaProducer.dto.KafkaMessage;
import ru.test.KafkaProducer.dto.MetricDto;

@Component
public class DtoMessageConverter {

    public KafkaMessage toMessage(MetricDto data){
        return KafkaMessage.builder()
                .name(data.getName())
                .description(data.getDescription())
                .baseUnit(data.getBaseUnit())
                .value(data.getMeasurements().get(0).getValue())
                .build();
    }
}
