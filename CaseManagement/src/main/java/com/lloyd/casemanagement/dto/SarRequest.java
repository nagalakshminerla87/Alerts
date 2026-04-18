package com.lloyd.casemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class SarRequest {

    @NotBlank
    private String decision;

    @NotBlank
    private String rationale;
}