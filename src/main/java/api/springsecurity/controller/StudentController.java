package api.springsecurity.controller;

import api.springsecurity.dtos.student.StudentRequestDto;
import api.springsecurity.dtos.student.StudentResponseDto;
import api.springsecurity.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> findAll(Pageable pageable) {
        Page<StudentResponseDto> page = studentService.findAllStudents(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto dto) {
        StudentResponseDto response = studentService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("#id == principal.id or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id,
                                                            @Valid @RequestBody StudentRequestDto dto) {
        StudentResponseDto response = studentService.update(id, dto);
        return ResponseEntity.ok(response);
    }
}