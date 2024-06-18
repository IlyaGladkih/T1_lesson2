package ru.test.KafkaConsumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Metric")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Getter
@Setter
public class Metric {

    @Id
    private String name;

    private String description;

    @Column(name = "baseunit")
    private String baseUnit;

    private double value;

    private double change;
}
