package com.lloyd.alerts.repository;

import com.lloyd.alerts.entity.Alerts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alerts, String>,JpaSpecificationExecutor<Alerts> {
}
