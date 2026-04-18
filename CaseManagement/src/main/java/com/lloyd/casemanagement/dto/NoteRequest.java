package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Data
public class NoteRequest {

    @NotBlank
    @Size(min = 10, max = 2000)
    private String text;

    @NotBlank
    private String author;
}