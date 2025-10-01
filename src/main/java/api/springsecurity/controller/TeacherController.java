package api.springsecurity.controller;

import api.springsecurity.dtos.student.StudentResponseDto;
import api.springsecurity.dtos.teacher.TeacherRequestDto;
import api.springsecurity.dtos.teacher.TeacherResponseDto;
import api.springsecurity.service.TeacherService;
import jakarta.servlet.ServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<Page<TeacherResponseDto>> findAll(Pageable pageable) {
        Page<TeacherResponseDto> page = teacherService.findAllTeachers(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDto> createTeacher(@Valid @RequestBody TeacherRequestDto dto) {
        TeacherResponseDto response = teacherService.create(dto);
        return ResponseEntity.ok(response);
    }
}