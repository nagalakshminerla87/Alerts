package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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