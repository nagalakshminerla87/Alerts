package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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