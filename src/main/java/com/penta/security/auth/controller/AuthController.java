package com.penta.security.auth.controller;

import com.penta.security.auth.dto.request.LoginRequestDto;
import com.penta.security.auth.dto.request.RegenerateTokenRequestDto;
import com.penta.security.auth.dto.response.TokenResponseDto;
import com.penta.security.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody LoginRequestDto requestDto) {
        TokenResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenResponseDto> refresh(
        @Valid @RequestBody RegenerateTokenRequestDto requestDto) {
        TokenResponseDto responseDto = authService.regenerateToken(requestDto);
        return ResponseEntity.ok(responseDto);
    }
}
