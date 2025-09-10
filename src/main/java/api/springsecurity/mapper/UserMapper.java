package api.springsecurity.mapper;

import api.springsecurity.dtos.user.UserRequestDto;
import api.springsecurity.dtos.user.UserResponseDto;
import api.springsecurity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDto dto);
    UserResponseDto toResponse(User user);
}