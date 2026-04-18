package com.lloyd.casemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteRequest {

    @NotBlank
    @Size(min = 10, max = 2000)
    private String text;

    @NotBlank
    private String author;
}