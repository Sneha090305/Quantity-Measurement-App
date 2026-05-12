package com.bridgelabz;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    TONNE(1000);

    private final double factor;

    WeightUnit(double factor) {
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