package com.bridgelabz;

public class QuantityMeasurementApp {

    // UC1
    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // UC2
    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // UC3–UC7
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }


        private double toBaseUnit() {
            return unit.toBase(value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Length other = (Length) obj;

            double difference = Math.abs(this.toBaseUnit() - other.toBaseUnit());
            return difference < 0.0001;
        }


        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            if (source == null || target == null)
                throw new IllegalArgumentException("Unit cannot be null");

            double baseValue = source.toBase(value);
            return target.fromBase(baseValue);
        }


        public Length convertTo(LengthUnit target) {
            double baseValue = unit.toBase(value);
            double convertedValue = target.fromBase(baseValue);
            return new Length(convertedValue, target);
        }

        // UC6
        public Length add(Length other) {
            if (other == null)
                throw new IllegalArgumentException("Length cannot be null");

            double sumBase = this.toBaseUnit() + other.toBaseUnit();


            double resultValue = unit.fromBase(sumBase);

            return new Length(resultValue, unit);
        }

        // UC7
        public Length add(Length other, LengthUnit targetUnit) {
            if (other == null)
                throw new IllegalArgumentException("Length cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double sumBase = this.toBaseUnit() + other.toBaseUnit();


            double resultValue = targetUnit.fromBase(sumBase);

            return new Length(resultValue, targetUnit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {

        Quantity l1 = new Quantity(10.0, LengthUnit.FEET);
        Quantity l2 = new Quantity(6.0, LengthUnit.INCHES);

        System.out.println("Subtraction Result: " + l1.subtract(l2));
        System.out.println("Division Result: " + l1.divide(l2));
    }
}