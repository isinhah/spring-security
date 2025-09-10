package api.springsecurity.service;

import api.springsecurity.dtos.student.StudentRequestDto;
import api.springsecurity.dtos.student.StudentResponseDto;
import api.springsecurity.entity.Student;
import api.springsecurity.mapper.StudentMapper;
import api.springsecurity.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentResponseDto create(StudentRequestDto dto) {
        // Mapear DTO → entidade
        Student student = StudentMapper.INSTANCE.toEntity(dto);

        // Criptografar a senha do user
        student.getUser().setPassword(passwordEncoder.encode(student.getUser().getPassword()));

        // Salvar no banco (cascade salva User também)
        Student savedStudent = studentRepository.save(student);

        // Retornar DTO de resposta
        return StudentMapper.INSTANCE.toResponse(savedStudent);
    }

    public StudentResponseDto update(Long id, StudentRequestDto dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        // Atualizar campos
        student.setName(dto.name());
        student.setCpf(dto.cpf());
        student.getUser().setEmail(dto.email());

        // Atualizar senha somente se diferente de null ou vazia
        if (dto.password() != null && !dto.password().isBlank()) {
            student.getUser().setPassword(passwordEncoder.encode(dto.password()));
        }

        Student updatedStudent = studentRepository.save(student);

        return StudentMapper.INSTANCE.toResponse(updatedStudent);
    }
}
