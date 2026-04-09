package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity add(double value1, double value2);

    QuantityMeasurementEntity subtract(double value1, double value2);

    QuantityMeasurementEntity compare(double value1, double value2);

    QuantityMeasurementEntity saveMeasurement(QuantityMeasurementEntity entity);
}