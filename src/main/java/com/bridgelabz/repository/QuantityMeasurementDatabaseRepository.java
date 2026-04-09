package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {
        System.out.println("Saved to DB: " + entity);
    }
}