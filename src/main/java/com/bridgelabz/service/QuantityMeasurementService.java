package com.bridgelabz.service;

import com.bridgelabz.*;
import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.dto.QuantityResponseDTO;
import com.bridgelabz.enums.MeasurementType;
import com.bridgelabz.enums.OperationType;
import org.springframework.stereotype.Service;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import java.util.List;

@Service
public class QuantityMeasurementService {

    public QuantityResponseDTO performOperation(
            QuantityRequestDTO request
    ) {

        IMeasurable firstUnit =
                getUnit(
                        request.getMeasurementType(),
                        request.getUnit()
                );

        IMeasurable secondUnit =
                getUnit(
                        request.getMeasurementType(),
                        request.getSecondUnit()
                );

        double firstBase =
                firstUnit.toBase(request.getValue());

        double secondBase =
                secondUnit.toBase(request.getSecondValue());

        OperationType operation =
                request.getOperationType();

        return switch (operation) {

            case ADD -> new QuantityResponseDTO(
                    "Addition Success",
                    firstBase + secondBase
            );

            case SUBTRACT -> new QuantityResponseDTO(
                    "Subtraction Success",
                    firstBase - secondBase
            );

            case COMPARE -> new QuantityResponseDTO(
                    Math.abs(firstBase - secondBase) < 0.0001
                            ? "Equal"
                            : "Not Equal",
                    Math.abs(firstBase - secondBase)
            );

            case CONVERT -> {

                double converted =
                        secondUnit.fromBase(firstBase);

                yield new QuantityResponseDTO(
                        "Conversion Success",
                        converted
                );
            }
        };
    }

    private IMeasurable getUnit(
            MeasurementType type,
            String unit
    ) {

        return switch (type) {

            case LENGTH ->
                    LengthUnit.valueOf(unit.toUpperCase());

            case WEIGHT ->
                    WeightUnit.valueOf(unit.toUpperCase());

            case VOLUME ->
                    VolumeUnit.valueOf(unit.toUpperCase());

            case TEMPERATURE ->
                    TemperatureUnit.valueOf(unit.toUpperCase());
        };
    }
}
