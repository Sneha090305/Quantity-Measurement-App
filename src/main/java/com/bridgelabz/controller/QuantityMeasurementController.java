package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }


    public boolean compare(QuantityDTO q1, QuantityDTO q2) {
        return service.compare(q1, q2);
    }

    public QuantityDTO convert(QuantityDTO q, String targetUnit) {
        return service.convert(q, targetUnit);
    }


    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {
        return service.add(q1, q2);
    }
}