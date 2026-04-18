-- Drop if exists (safe restart)
DROP TABLE IF EXISTS alert_flagged_rules;
DROP TABLE IF EXISTS alert;

CREATE TABLE alert (
                       alert_id VARCHAR(50) PRIMARY KEY,
                       customer_id VARCHAR(50) NOT NULL,
                       alert_type VARCHAR(50) NOT NULL,
                       risk_band VARCHAR(10) NOT NULL,
                       amount DECIMAL(15,2) NOT NULL,
                       currency VARCHAR(3) NOT NULL,
                       triggered_at TIMESTAMP,
                       status VARCHAR(20),
                       assigned_analyst VARCHAR(100)
);

-- ElementCollection table for flaggedRules
CREATE TABLE alert_flagged_rules (
                                     alert_id VARCHAR(50),
                                     flagged_rules VARCHAR(100)
);