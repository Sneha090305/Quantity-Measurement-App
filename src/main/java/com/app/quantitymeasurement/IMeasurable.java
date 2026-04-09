package com.app.quantitymeasurement;

public interface IMeasurable {

    double toBase(double value);

    double fromBase(double value);
}