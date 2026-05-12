package com.bridgelabz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuantityResponseDTO {

    private String message;

    private double result;
}