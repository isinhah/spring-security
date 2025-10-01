package api.springsecurity.controller;

import api.springsecurity.dtos.auth.LoginRequestDto;
import api.springsecurity.dtos.auth.LoginResponseDto;
import api.springsecurity.dtos.user.UserRequestDto;
import api.springsecurity.dtos.user.UserResponseDto;
import api.springsecurity.service.AdminService;
import api.springsecurity.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto dto) {
        LoginResponseDto response = authService.login(dto);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admins")
    public ResponseEntity<UserResponseDto> createAdmin(@Valid @RequestBody UserRequestDto dto) {
        UserResponseDto response = adminService.createAdmin(dto);
        return ResponseEntity.ok(response);
    }
}