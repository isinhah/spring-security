package api.springsecurity.service;

import api.springsecurity.dtos.auth.LoginRequestDto;
import api.springsecurity.dtos.auth.LoginResponseDto;
import api.springsecurity.entity.User;
import api.springsecurity.repository.UserRepository;
import api.springsecurity.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = jwtService.generateToken(user);

        Instant expiresAt = jwtService.generateExpirationDate();

        return new LoginResponseDto(token, user.getRole().name(), expiresAt);
    }

}
