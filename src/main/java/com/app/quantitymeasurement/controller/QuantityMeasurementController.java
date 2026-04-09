package com.app.quantitymeasurement.controller;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quantity")
public class QuantityMeasurementController {

    @Autowired
    private QuantityMeasurementServiceImpl service;


    @GetMapping("/test")
    public String test() {
        return "API is working ";
    }


    @PostMapping("/add")
    public QuantityMeasurementEntity add(
            @RequestParam double value1,
            @RequestParam double value2) {

        return service.add(value1, value2);
    }

    @PostMapping("/subtract")
    public QuantityMeasurementEntity subtract(
            @RequestParam double value1,
            @RequestParam double value2) {

        return service.subtract(value1, value2);
    }

    @PostMapping("/compare")
    public QuantityMeasurementEntity compare(
            @RequestParam double value1,
            @RequestParam double value2) {

        return service.compare(value1, value2);
    }


    @PostMapping("/save")
    public QuantityMeasurementEntity save(@RequestBody QuantityMeasurementEntity entity) {
        return service.saveMeasurement(entity);
    }

    @GetMapping
    public String home() {
        return "Quantity API running";
    }
}