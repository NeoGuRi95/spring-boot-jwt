package com.penta.security.controller;

import com.penta.security.dto.request.SystemUserCreateRequestDto;
import com.penta.security.dto.request.SystemUserUpdateRequestDto;
import com.penta.security.dto.response.SystemUserResponseDto;
import com.penta.security.service.SystemUserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final SystemUserService systemUserService;

    @GetMapping
    public ResponseEntity<List<SystemUserResponseDto>> getUsers(
        @RequestParam(required = false) String userId,
        @RequestParam(required = false) String userNm) {
        List<SystemUserResponseDto> responseDtoList =
            systemUserService.getSystemUserList(userId, userNm);
        return ResponseEntity.ok(responseDtoList);
    }

    @PostMapping
    public ResponseEntity<SystemUserResponseDto> create(
        @RequestBody @Valid SystemUserCreateRequestDto requestDto) {
        SystemUserResponseDto responseDto = systemUserService.create(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<SystemUserResponseDto> update(@PathVariable String userId,
        @RequestBody @Valid SystemUserUpdateRequestDto requestDto) {
        SystemUserResponseDto responseDto = systemUserService.update(userId, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<SystemUserResponseDto> delete(@PathVariable String userId) {
        SystemUserResponseDto responseDto = systemUserService.delete(userId);
        return ResponseEntity.ok(responseDto);
    }
}
