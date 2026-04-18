package com.lloyd.casemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lloyd.casemanagement.entity.CaseEntity;

public interface CaseRepository extends JpaRepository<CaseEntity, String> {
}