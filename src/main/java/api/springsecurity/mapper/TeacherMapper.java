package api.springsecurity.mapper;

import api.springsecurity.dtos.teacher.TeacherRequestDto;
import api.springsecurity.dtos.teacher.TeacherResponseDto;
import api.springsecurity.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "user.password", source = "password")
    @Mapping(target = "user.role", constant = "TEACHER")
    Teacher toEntity(TeacherRequestDto dto);

    TeacherResponseDto toResponse(Teacher teacher);
}