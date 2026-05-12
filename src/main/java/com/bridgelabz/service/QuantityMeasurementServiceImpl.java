package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityInputDTO;
import com.bridgelabz.dto.QuantityMeasurementDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final List<QuantityMeasurementDTO> history = new ArrayList<>();

    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {

        boolean equal = input.getValue1() == input.getValue2();

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO(
                        equal ? "Equal Quantities" : "Not Equal",
                        0
                );

        history.add(response);

        return response;
    }

    @Override
    public QuantityMeasurementDTO add(QuantityInputDTO input) {

        double result = input.getValue1() + input.getValue2();

        QuantityMeasurementDTO response =
                new QuantityMeasurementDTO(
                        "Addition Successful",
                        result
                );

        history.add(response);

        return response;
    }

    @Override
    public List<QuantityMeasurementDTO> getHistory(String operation) {
        return history;
    }
}