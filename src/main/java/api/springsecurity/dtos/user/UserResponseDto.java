package api.springsecurity.dtos.user;

import api.springsecurity.entity.Role;

public record UserResponseDto(
        Long id,
        String email,
        Role role
) {
}