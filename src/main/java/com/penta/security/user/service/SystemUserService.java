package com.penta.security.user.service;

import com.penta.security.user.dto.request.SystemUserCreateRequestDto;
import com.penta.security.user.dto.request.SystemUserUpdateRequestDto;
import com.penta.security.user.dto.response.SystemUserResponseDto;
import com.penta.security.user.entity.SystemUser;
import com.penta.security.user.repository.SystemUserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemUserService {

    private final SystemUserRepository systemUserRepository;

    public SystemUser getSystemUserEntity(String userId) {
        Optional<SystemUser> opSystemUser = systemUserRepository.findByUserId(userId);
        return opSystemUser.orElseThrow(() -> new RuntimeException("해당하는 유저가 없습니다."));
    }

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
        SystemUser systemUser = getSystemUserEntity(userId);

        systemUser.setUserNm(requestDto.getUserNm());
        SystemUser updatedSystemUser = systemUserRepository.save(systemUser);

        return new SystemUserResponseDto(updatedSystemUser);
    }

    public SystemUserResponseDto delete(String userId) {
        SystemUser deletedSystemUser = getSystemUserEntity(userId);

        systemUserRepository.delete(deletedSystemUser);

        return new SystemUserResponseDto(deletedSystemUser);
    }
}
