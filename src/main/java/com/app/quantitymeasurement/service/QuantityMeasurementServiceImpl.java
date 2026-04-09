package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    @Override
    public QuantityMeasurementEntity saveMeasurement(QuantityMeasurementEntity entity) {
        return repository.save(entity);
    }

    @Override
    public QuantityMeasurementEntity add(double value1, double value2) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        try {
            entity.setOperation("ADD");
            entity.setValue1(value1);
            entity.setValue2(value2);
            entity.setResult(value1 + value2);
            entity.setError(false);

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
        }

        return repository.save(entity);
    }

    @Override
    public QuantityMeasurementEntity subtract(double value1, double value2) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        try {
            entity.setOperation("SUBTRACT");
            entity.setValue1(value1);
            entity.setValue2(value2);
            entity.setResult(value1 - value2);
            entity.setError(false);

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
        }

        return repository.save(entity);
    }

    @Override
    public QuantityMeasurementEntity compare(double value1, double value2) {

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        try {
            entity.setOperation("COMPARE");
            entity.setValue1(value1);
            entity.setValue2(value2);
            entity.setResult(value1 == value2 ? 1.0 : 0.0);
            entity.setError(false);

        } catch (Exception e) {
            entity.setError(true);
            entity.setErrorMessage(e.getMessage());
        }

        return repository.save(entity);
    }
}