package com.bridgelabz.service;

import com.bridgelabz.*;
import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.dto.QuantityResponseDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.enums.MeasurementType;
import com.bridgelabz.repository.QuantityMeasurementDatabaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementDatabaseRepository
            quantityMeasurementDatabaseRepository;

    @Override
    public QuantityResponseDTO performOperation(
            QuantityRequestDTO request
    ) {

        IMeasurable firstUnit = getUnit(
                request.getMeasurementType(),
                request.getUnit()
        );

        IMeasurable secondUnit = getUnit(
                request.getMeasurementType(),
                request.getSecondUnit()
        );

        double firstBase =
                firstUnit.toBase(request.getValue());

        double secondBase =
                secondUnit.toBase(request.getSecondValue());

        double result = 0;
        String message = "";

        switch (request.getOperationType()) {

            case ADD -> {
                result = firstBase + secondBase;
                message = "Addition Success";
            }

            case SUBTRACT -> {
                result = firstBase - secondBase;
                message = "Subtraction Success";
            }

            case COMPARE -> {

                if (Double.compare(firstBase, secondBase) == 0) {
                    message = "Equal";
                    result = 1;
                } else {
                    message = "Not Equal";
                    result = 0;
                }
            }

            case CONVERT -> {
                result = secondUnit.fromBase(firstBase);
                message = "Conversion Success";
            }
        }

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity();

        entity.setValue(request.getValue());
        entity.setUnit(request.getUnit());
        entity.setMeasurementType(
                request.getMeasurementType().name()
        );
        entity.setOperationType(
                request.getOperationType().name()
        );
        entity.setSecondValue(
                request.getSecondValue()
        );
        entity.setSecondUnit(
                request.getSecondUnit()
        );
        entity.setResult(result);

        quantityMeasurementDatabaseRepository.save(entity);

        return new QuantityResponseDTO(
                message,
                result
        );
    }

    @Override
    public List<QuantityMeasurementEntity> getAllHistory() {

        return quantityMeasurementDatabaseRepository.findAll();
    }

    @Override
    public List<QuantityMeasurementEntity> getHistoryByType(
            String operationType
    ) {

        return quantityMeasurementDatabaseRepository
                .findByOperationType(operationType);
    }

    @Override
    public Long getCountByType(
            String operationType
    ) {

        return quantityMeasurementDatabaseRepository
                .countByOperationType(operationType);
    }

    private IMeasurable getUnit(
            MeasurementType type,
            String unit
    ) {

        return switch (type) {

            case LENGTH ->
                    LengthUnit.valueOf(unit);

            case WEIGHT ->
                    WeightUnit.valueOf(unit);

            case VOLUME ->
                    VolumeUnit.valueOf(unit);

            case TEMPERATURE ->
                    TemperatureUnit.valueOf(unit);
        };
    }
}