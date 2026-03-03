package com.bridgelabz;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
feature/UC9-weight-measurement
    CENTIMETERS(1.0 / 30.48);
    CENTIMETERS(0.0328084);
develop

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

 feature/UC9-weight-measurement
    // Convert value in this unit → FEET (base unit)
    // convert given value to FEET
develop
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

feature/UC9-weight-measurement
    // Convert FEET (base unit) → this unit
    // convert FEET to target unit
develop
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }
}