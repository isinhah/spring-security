package api.springsecurity.service;

import api.springsecurity.dtos.student.StudentRequestDto;
import api.springsecurity.dtos.student.StudentResponseDto;
import api.springsecurity.entity.Role;
import api.springsecurity.entity.Student;
import api.springsecurity.mapper.StudentMapper;
import api.springsecurity.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<StudentResponseDto> findAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable)
                .map(StudentMapper.INSTANCE::toResponse);
    }

    @Transactional
    public StudentResponseDto create(StudentRequestDto dto) {
        Student student = StudentMapper.INSTANCE.toEntity(dto);

        student.getUser().setRole(Role.STUDENT);
        student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.INSTANCE.toResponse(savedStudent);
    }

    @Transactional
    public StudentResponseDto update(Long id, StudentRequestDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        student.setName(dto.name());
        student.setCpf(dto.cpf());
        student.getUser().setEmail(dto.email());

        if (dto.password() != null && !dto.password().isBlank()) {
            student.getUser().setPassword(passwordEncoder.encode(dto.password()));
        }

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.INSTANCE.toResponse(updatedStudent);
    }
}
