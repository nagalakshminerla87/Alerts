package com.lloyd.alerts.controller;

import com.lloyd.alerts.dto.AlertRequest;
import com.lloyd.alerts.entity.Alert;
import com.lloyd.alerts.service.AlertService;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertsController {

    private final AlertService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AlertRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping
    public Page<Alert> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String riskBand,
            Pageable pageable) {

        Specification<Alert> spec = (root, q, cb) -> {
            List<Predicate> p = new ArrayList<>();
            if (status != null) p.add(cb.equal(root.get("status"), status));
            if (riskBand != null) p.add(cb.equal(root.get("riskBand"), riskBand));
            return cb.and(p.toArray(new Predicate[0]));
        };

        return service.getAll(spec, pageable);
    }

    @GetMapping("/{id}")
    public Alert getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}/status")
    public void update(@PathVariable String id, @RequestParam String status) {
        service.updateStatus(id, status);
    }
}
