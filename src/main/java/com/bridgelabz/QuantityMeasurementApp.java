package com.bridgelabz;

import com.bridgelabz.repository.IQuantityMeasurementRepository;
import com.bridgelabz.repository.QuantityMeasurementDatabaseRepository;
import com.bridgelabz.service.IQuantityMeasurementService;
import com.bridgelabz.service.QuantityMeasurementServiceImpl;
import com.bridgelabz.controller.QuantityMeasurementController;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // UC16 Wiring
        IQuantityMeasurementRepository repo =
                new QuantityMeasurementDatabaseRepository();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        System.out.println("Application started with DB");
    }

    // ================= UC1 =================
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

    // ================= UC2 =================
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

    // ================= UC3–UC7 =================
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

            double diff = Math.abs(this.toBaseUnit() - other.toBaseUnit());
            return diff < 0.0001;
        }

        // Conversion
        public static double convert(double value, LengthUnit source, LengthUnit target) {
            double baseValue = source.toBase(value);
            return target.fromBase(baseValue);
        }

        public Length convertTo(LengthUnit target) {
            double baseValue = unit.toBase(value);
            double convertedValue = target.fromBase(baseValue);
            return new Length(convertedValue, target);
        }

        // Addition
        public Length add(Length other) {
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double result = unit.fromBase(sumBase);
            return new Length(result, unit);
        }

        public Length add(Length other, LengthUnit targetUnit) {
            double sumBase = this.toBaseUnit() + other.toBaseUnit();
            double result = targetUnit.fromBase(sumBase);
            return new Length(result, targetUnit);
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }
}