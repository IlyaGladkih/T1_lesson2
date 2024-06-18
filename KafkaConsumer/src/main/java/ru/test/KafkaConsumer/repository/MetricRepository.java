package ru.test.KafkaConsumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.KafkaConsumer.entity.Metric;

public interface MetricRepository extends JpaRepository<Metric, String> {
}
