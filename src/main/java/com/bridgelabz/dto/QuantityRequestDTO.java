package com.bridgelabz.dto;

import com.bridgelabz.enums.MeasurementType;
import com.bridgelabz.enums.OperationType;
import lombok.Data;

@Data
public class QuantityRequestDTO {

    private double value;

    private String unit;

    private MeasurementType measurementType;

    private OperationType operationType;

    private double secondValue;

    private String secondUnit;
}