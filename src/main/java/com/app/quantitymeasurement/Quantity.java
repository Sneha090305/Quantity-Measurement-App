package com.app.quantitymeasurement;

public class Quantity {

    private double value;
    private IMeasurable unit;


    public Quantity(double value, IMeasurable unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public IMeasurable getUnit() {
        return unit;
    }


    public Quantity convertTo(IMeasurable targetUnit) {
        double baseValue = unit.toBase(value);
        double convertedValue = targetUnit.fromBase(baseValue);
        return new Quantity(convertedValue, targetUnit);
    }


    public Quantity add(Quantity other) {
        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        double sum = base1 + base2;

        double result = this.unit.fromBase(sum);

        return new Quantity(result, this.unit);
    }


    public Quantity subtract(Quantity other) {
        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        double diff = base1 - base2;

        double result = this.unit.fromBase(diff);

        return new Quantity(result, this.unit);
    }


    public double divide(Quantity other) {
        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        return base1 / base2;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Quantity)) return false;

        Quantity other = (Quantity) obj;

        double base1 = this.unit.toBase(this.value);
        double base2 = other.unit.toBase(other.value);

        return Math.abs(base1 - base2) < 0.0001;
    }
}