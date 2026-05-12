package com.bridgelabz.controller;

import com.bridgelabz.dto.QuantityRequestDTO;
import com.bridgelabz.dto.QuantityResponseDTO;
import com.bridgelabz.entity.QuantityMeasurementEntity;
import com.bridgelabz.service.IQuantityMeasurementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService quantityMeasurementService;

    @PostMapping("/operate")
    public ResponseEntity<QuantityResponseDTO> performOperation(
            @RequestBody QuantityRequestDTO request
    ) {

        return ResponseEntity.ok(
                quantityMeasurementService.performOperation(request)
        );
    }

    @GetMapping("/history")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getAllHistory() {

        return ResponseEntity.ok(
                quantityMeasurementService.getAllHistory()
        );
    }

    @GetMapping("/history/{operationType}")
    public ResponseEntity<List<QuantityMeasurementEntity>>
    getHistoryByType(
            @PathVariable String operationType
    ) {

        return ResponseEntity.ok(
                quantityMeasurementService
                        .getHistoryByType(operationType)
        );
    }

    @GetMapping("/count/{operationType}")
    public ResponseEntity<Long>
    getCountByType(
            @PathVariable String operationType
    ) {

        return ResponseEntity.ok(
                quantityMeasurementService
                        .getCountByType(operationType)
        );
    }
}