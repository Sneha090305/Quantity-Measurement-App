package com.bridgelabz.service;

import com.bridgelabz.*;
import com.bridgelabz.dto.QuantityDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.exception.QuantityMeasurementException;
import com.bridgelabz.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }


    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        Quantity quantity1 = new Quantity(q1.getValue(), getUnit(q1));
        Quantity quantity2 = new Quantity(q2.getValue(), getUnit(q2));

        boolean result = quantity1.equals(quantity2);

        repository.save(new QuantityMeasurementEntity("COMPARE", String.valueOf(result)));

        return result;
    }


    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        Quantity quantity = new Quantity(q.getValue(), getUnit(q));

        IMeasurable target = getUnitByName(targetUnit);

        Quantity converted = quantity.convertTo(target);

        repository.save(new QuantityMeasurementEntity("CONVERT", String.valueOf(converted.getValue())));

        return new QuantityDTO(converted.getValue(), targetUnit, q.getMeasurementType());
    }


    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        Quantity quantity1 = new Quantity(q1.getValue(), getUnit(q1));
        Quantity quantity2 = new Quantity(q2.getValue(), getUnit(q2));

        Quantity result = quantity1.add(quantity2);

        repository.save(new QuantityMeasurementEntity("ADD", String.valueOf(result.getValue())));

        return new QuantityDTO(result.getValue(), q1.getUnit(), q1.getMeasurementType());
    }

    // 🔹 HELPER METHODS
    private IMeasurable getUnit(QuantityDTO dto) {
        return getUnitByName(dto.getUnit());
    }

    private IMeasurable getUnitByName(String unitName) {

        try { return LengthUnit.valueOf(unitName.toUpperCase()); } catch (Exception ignored) {}
        try { return WeightUnit.valueOf(unitName.toUpperCase()); } catch (Exception ignored) {}
        try { return VolumeUnit.valueOf(unitName.toUpperCase()); } catch (Exception ignored) {}
        try { return TemperatureUnit.valueOf(unitName.toUpperCase()); } catch (Exception ignored) {}

        throw new QuantityMeasurementException("Invalid Unit: " + unitName);
    }
}