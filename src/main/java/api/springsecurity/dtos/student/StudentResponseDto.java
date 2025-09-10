package api.springsecurity.dtos.student;

public record StudentResponseDto(
        Long id,
        String name,
        String cpf
) {}