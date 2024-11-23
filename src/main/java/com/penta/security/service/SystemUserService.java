package com.penta.security.service;

import com.penta.security.dto.request.SystemUserCreateRequestDto;
import com.penta.security.dto.request.SystemUserUpdateRequestDto;
import com.penta.security.dto.response.SystemUserResponseDto;
import com.penta.security.entity.SystemUser;
import com.penta.security.repository.SystemUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemUserService {

    private final SystemUserRepository systemUserRepository;

    public List<SystemUserResponseDto> getSystemUserList(String userId, String userNm) {
        List<SystemUser> systemUserList = systemUserRepository.findByUserIdContainingOrUserNmContaining(
            userId, userNm);
        return systemUserList.stream().map(SystemUserResponseDto::new).toList();
    }

    public SystemUserResponseDto create(SystemUserCreateRequestDto requestDto) {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserId(requestDto.getUserId());
        systemUser.setUserPw(new BCryptPasswordEncoder().encode(requestDto.getUserPw()));
        systemUser.setUserNm(requestDto.getUserNm());
        systemUser.setUserAuth("SYSTEM_USER");
        SystemUser createdSystemUser = systemUserRepository.save(systemUser);

        return new SystemUserResponseDto(createdSystemUser);
    }

    public SystemUserResponseDto update(String userId, SystemUserUpdateRequestDto requestDto) {
        SystemUser systemUser = systemUserRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("System User not found"));
        systemUser.setUserNm(requestDto.getUserNm());
        SystemUser updatedSystemUser = systemUserRepository.save(systemUser);

        return new SystemUserResponseDto(updatedSystemUser);
    }

    public SystemUserResponseDto delete(String userId) {
        SystemUser deletedSystemUser = systemUserRepository.findByUserId(userId)
            .orElseThrow(() -> new RuntimeException("System User not found"));
        systemUserRepository.delete(deletedSystemUser);

        return new SystemUserResponseDto(deletedSystemUser);
    }
}
