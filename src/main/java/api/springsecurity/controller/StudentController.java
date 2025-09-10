package api.springsecurity.controller;

import api.springsecurity.dtos.student.StudentRequestDto;
import api.springsecurity.dtos.student.StudentResponseDto;
import api.springsecurity.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentRequestDto dto) {
        StudentResponseDto response = studentService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id,
                                                            @Valid @RequestBody StudentRequestDto dto) {
        StudentResponseDto response = studentService.update(id, dto);
        return ResponseEntity.ok(response);
    }
}