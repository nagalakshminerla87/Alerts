package com.lloyd.casemanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.lloyd.casemanagement.entity.CaseEntity;
import com.lloyd.casemanagement.dto.CaseRequest;
import com.lloyd.casemanagement.dto.NoteRequest;
import com.lloyd.casemanagement.dto.SarRequest;
import com.lloyd.casemanagement.service.CaseService;

@RestController
@RequestMapping("/api/cases")
@RequiredArgsConstructor
public class CaseController {

    private final CaseService service;

    @PostMapping
    public CaseEntity create(@Valid @RequestBody CaseRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<CaseEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CaseEntity get(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping("/{id}/notes")
    public CaseEntity addNote(@PathVariable String id,
                              @Valid @RequestBody NoteRequest req) {
        return service.addNote(id, req);
    }

    @PostMapping("/{id}/sar")
    public CaseEntity fileSar(@PathVariable String id,
                              @Valid @RequestBody SarRequest req) {
        return service.fileSar(id, req);
    }
}