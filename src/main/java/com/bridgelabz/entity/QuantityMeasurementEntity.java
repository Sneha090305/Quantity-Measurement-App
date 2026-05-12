package com.bridgelabz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double value;

    private String unit;

    private String measurementType;

    private String operationType;

    private double secondValue;

    private String secondUnit;

    private double result;
}