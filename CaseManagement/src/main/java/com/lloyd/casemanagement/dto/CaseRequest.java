package com.lloyd.casemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
public class CaseRequest {

    @NotEmpty
    private List<String> linkedAlertIds;

    @NotBlank
    private String customerId;

    @NotBlank
    private String assignedAnalyst;

    @Pattern(regexp = "HIGH|MEDIUM|LOW")
    private String priority;
}