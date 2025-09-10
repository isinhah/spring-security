package api.springsecurity.dtos.user;

import api.springsecurity.entity.Role;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDto(
        @NotBlank String email,
        @NotBlank String password,
        Role role
) {}