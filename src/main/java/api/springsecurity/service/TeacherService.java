package api.springsecurity.service;

import api.springsecurity.dtos.teacher.TeacherRequestDto;
import api.springsecurity.dtos.teacher.TeacherResponseDto;
import api.springsecurity.entity.Role;
import api.springsecurity.entity.Teacher;
import api.springsecurity.mapper.TeacherMapper;
import api.springsecurity.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    public Page<TeacherResponseDto> findAllTeachers(Pageable pageable) {
        return teacherRepository.findAll(pageable)
                .map(TeacherMapper.INSTANCE::toResponse);
    }

    @Transactional
    public TeacherResponseDto create(TeacherRequestDto dto) {
        Teacher teacher = TeacherMapper.INSTANCE.toEntity(dto);

        teacher.getUser().setRole(Role.TEACHER);
        teacher.getUser().setPassword(passwordEncoder.encode(teacher.getUser().getPassword()));

        Teacher savedTeacher = teacherRepository.save(teacher);

        return TeacherMapper.INSTANCE.toResponse(savedTeacher);
    }
}
