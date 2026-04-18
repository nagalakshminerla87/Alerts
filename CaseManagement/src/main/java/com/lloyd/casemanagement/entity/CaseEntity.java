package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Entity
@Data
@Table(name = "cases")
public class CaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String caseId;

    @NotBlank
    private String customerId;

    @NotBlank
    private String priority;

    @NotBlank
    private String assignedAnalyst;

    private String status;
    private LocalDateTime openedAt;

    @ElementCollection
    private List<String> linkedAlertIds = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<AuditLog> auditLog = new ArrayList<>();
}