package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
        System.out.println("Saved in Cache: " + entity);
    }

    public List<QuantityMeasurementEntity> getAll() {
        return cache;
    }
}