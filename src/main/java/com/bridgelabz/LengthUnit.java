package com.bridgelabz;
public enum LengthUnit implements IMeasurable {

    METER(1.0),
    CENTIMETER(0.01),
    INCH(0.0254),
    FEET(0.3048),
    YARD(0.9144);

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