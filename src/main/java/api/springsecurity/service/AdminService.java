package api.springsecurity.service;

import api.springsecurity.dtos.user.UserRequestDto;
import api.springsecurity.dtos.user.UserResponseDto;
import api.springsecurity.entity.Role;
import api.springsecurity.entity.User;
import api.springsecurity.mapper.UserMapper;
import api.springsecurity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto createAdmin(UserRequestDto dto) {
        User user = UserMapper.INSTANCE.toEntity(dto);

        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedAdmin = userRepository.save(user);

        return UserMapper.INSTANCE.toResponse(savedAdmin);
    }
}
