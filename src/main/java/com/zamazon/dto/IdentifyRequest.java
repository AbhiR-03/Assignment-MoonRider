package com.zamazon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class IdentifyRequest {
    @NotBlank(message = "Email must not be blank")
    private String email;
    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;
}