package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityInputDTO;
import com.bridgelabz.dto.QuantityMeasurementDTO;

import java.util.List;

public interface IQuantityMeasurementService {

    QuantityMeasurementDTO compare(QuantityInputDTO input);

    QuantityMeasurementDTO add(QuantityInputDTO input);

    List<QuantityMeasurementDTO> getHistory(String operation);
}