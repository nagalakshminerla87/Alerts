package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String text;
    private String author;
    private LocalDateTime createdAt;
}