package com.bridgelabz.service;

import com.bridgelabz.dto.QuantityDTO;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2);

    QuantityDTO convert(QuantityDTO q, String targetUnit);

    QuantityDTO add(QuantityDTO q1, QuantityDTO q2);
}