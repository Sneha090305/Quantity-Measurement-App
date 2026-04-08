package com.bridgelabz;

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12),
    YARD(3.0),
    CM(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double toBase(double value) {
        return value * factor;
    }

    @Override
    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}