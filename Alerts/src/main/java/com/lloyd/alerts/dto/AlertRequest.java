package com.lloyd.alerts.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class AlertRequest {

    @NotBlank
    private String customerId;

    @NotBlank
    private String alertType;

    @Pattern(regexp = "HIGH|MEDIUM|LOW")
    private String riskBand;

    @DecimalMin("0.01")
    private Double amount;

    @Size(min = 3, max = 3)
    private String currency;

    @PastOrPresent
    private Instant triggeredAt;

    @NotEmpty
    private List<String> flaggedRules;
}