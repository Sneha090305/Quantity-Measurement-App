package com.bridgelabz;

public enum VolumeUnit implements IMeasurable {

    LITER(1.0),
    MILLILITER(0.001);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double toBase(double value) {
        return value * factor;
    }

    @Override
    public double fromBase(double value) {
        return value / factor;
    }
}