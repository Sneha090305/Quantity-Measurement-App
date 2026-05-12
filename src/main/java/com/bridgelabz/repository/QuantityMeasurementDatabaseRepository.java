package com.bridgelabz.repository;

import com.bridgelabz.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuantityMeasurementDatabaseRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    List<QuantityMeasurementEntity> findByOperationType(
            String operationType
    );

    Long countByOperationType(
            String operationType
    );
}