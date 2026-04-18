package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository repo;

    public CaseEntity create(CaseRequest req) {
        CaseEntity c = new CaseEntity();

        BeanUtils.copyProperties(req, c);
        c.setStatus("OPEN");
        c.setOpenedAt(LocalDateTime.now());

        addAudit(c, "CASE_CREATED", "Case opened");

        return repo.save(c);
    }

    public List<CaseEntity> getAll() {
        return repo.findAll();
    }

    public CaseEntity getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Case not found"));
    }

    public CaseEntity addNote(String id, NoteRequest req) {
        CaseEntity c = getById(id);

        Note note = new Note();
        BeanUtils.copyProperties(req, note);
        note.setCreatedAt(LocalDateTime.now());

        c.getNotes().add(note);

        addAudit(c, "NOTE_ADDED", "Note added by " + req.getAuthor());

        return repo.save(c);
    }

    public CaseEntity fileSar(String id, SarRequest req) {
        CaseEntity c = getById(id);

        if (!c.getStatus().equals("PENDING_REVIEW")) {
            throw new IllegalStateException("SAR only allowed in PENDING_REVIEW");
        }

        if (req.getDecision().equals("FILE")) {
            c.setStatus("SAR_FILED");
        } else {
            c.setStatus("NO_ACTION_TAKEN");
        }

        addAudit(c, "SAR_DECISION", req.getRationale());

        return repo.save(c);
    }

    private void addAudit(CaseEntity c, String action, String details) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setDetails(details);
        log.setTimestamp(LocalDateTime.now());

        c.getAuditLog().add(log);
    }
}