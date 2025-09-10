package api.springsecurity.dtos.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record StudentRequestDto(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "CPF must be provided")
        @Pattern(regexp = "\\d{11}", message = "CPF must contain exactly 11 digits")
        String cpf,

        @NotBlank(message = "Email is required")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {}