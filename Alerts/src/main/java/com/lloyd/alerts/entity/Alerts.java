package com.lloyd.alerts.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class Alerts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String alertId;

    private String customerId;
    private String alertType;
    private String riskBand;
    private Double amount;
    private String currency;
    private Instant triggeredAt;
    private String status;
    private String assignedAnalyst;

    @ElementCollection
    private List<String> flaggedRules;
}