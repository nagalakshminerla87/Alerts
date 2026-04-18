package com.lloyd.alerts.service;

import com.lloyd.alerts.dto.AlertRequest;
import com.lloyd.alerts.entity.Alerts;
import com.lloyd.alerts.repository.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository repo;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Alerts create(AlertRequest req) {
        Alerts alert = new Alerts();
        BeanUtils.copyProperties(req, alert);
        alert.setStatus("NEW");

        Alerts saved = repo.save(alert);

        // Kafka event
        kafkaTemplate.send("alert-events", saved);

        return saved;
    }

    public Page<Alerts> getAll(Specification<Alerts> spec, Pageable pageable) {
        return repo.findAll(spec, pageable);
    }

    public Alerts getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Alert not found"));
    }

    public void updateStatus(String id, String newStatus) {
        Alerts alert = getById(id);

        List<String> valid = List.of("NEW","UNDER_REVIEW","ESCALATED","CLOSED");

        if (!valid.contains(newStatus)) {
            throw new RuntimeException("Invalid status");
        }

        alert.setStatus(newStatus);
        repo.save(alert);
    }
}
