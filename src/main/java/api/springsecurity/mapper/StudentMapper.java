package api.springsecurity.mapper;

import api.springsecurity.dtos.student.StudentRequestDto;
import api.springsecurity.dtos.student.StudentResponseDto;
import api.springsecurity.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "user.password", source = "password")
    @Mapping(target = "user.role", constant = "STUDENT")
    Student toEntity(StudentRequestDto dto);

    StudentResponseDto toResponse(Student student);
}