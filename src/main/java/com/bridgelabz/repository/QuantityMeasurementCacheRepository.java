package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    // In-memory storage
    private final List<QuantityMeasurementEntity> storage = new ArrayList<>();

    // Save entity
    @Override
    public void save(QuantityMeasurementEntity entity) {
        storage.add(entity);
    }

    // Get all records
    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return storage;
    }
}