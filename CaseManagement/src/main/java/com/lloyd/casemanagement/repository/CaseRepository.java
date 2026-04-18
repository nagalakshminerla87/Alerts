package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
public interface CaseRepository extends JpaRepository<CaseEntity, String> {
}