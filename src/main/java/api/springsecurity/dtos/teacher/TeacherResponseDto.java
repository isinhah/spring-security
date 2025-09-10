package api.springsecurity.dtos.teacher;

public record TeacherResponseDto(
        Long id,
        String name,
        String email
) {
}