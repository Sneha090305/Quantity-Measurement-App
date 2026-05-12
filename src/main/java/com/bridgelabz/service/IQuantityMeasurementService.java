package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.dto.QuantityResponseDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

    QuantityResponseDTO performOperation(
            QuantityRequestDTO request
    );

    List<QuantityMeasurementEntity> getAllHistory();

    List<QuantityMeasurementEntity> getHistoryByType(
            String operationType
    );

    Long getCountByType(
            String operationType
    );
}