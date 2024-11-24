package com.penta.security.auth.service;

import com.penta.security.auth.dto.request.LoginRequestDto;
import com.penta.security.auth.dto.request.RegenerateTokenRequestDto;
import com.penta.security.auth.dto.response.TokenResponseDto;
import com.penta.security.auth.vo.RefreshTokenStore;
import com.penta.security.user.entity.SystemUser;
import com.penta.security.auth.jwt.JwtProvider;
import com.penta.security.user.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final SystemUserService systemUserService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;

    private final RefreshTokenStore refreshTokenStore;

    @Transactional
    public TokenResponseDto login(LoginRequestDto requestDto) {
        // 사용자 정보 조회
        SystemUser systemUser = systemUserService.getSystemUserEntity(requestDto.getUserId());

        // password 일치 여부 체크
        if(!bCryptPasswordEncoder.matches(requestDto.getUserPw(), systemUser.getUserPw()))
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");

        // jwt 토큰 생성
        String userId = systemUser.getUserId();
        String accessToken = jwtProvider.generateAccessToken(userId);

        // 기존에 가지고 있는 사용자의 refresh token 제거
        refreshTokenStore.remove(userId);

        // refresh token 생성 후 저장
        String refreshToken = jwtProvider.generateRefreshToken(userId);
        refreshTokenStore.put(userId, refreshToken);

        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setAccessToken(accessToken);
        responseDto.setRefreshToken(refreshToken);

        return responseDto;
    }

    @Transactional
    public TokenResponseDto regenerateToken(RegenerateTokenRequestDto requestDto) {
        String userId = requestDto.getUserId();

        // refresh token 유효성 검증
        String requestedRefreshToken = requestDto.getRefreshToken();
        if(!jwtProvider.validateToken(requestedRefreshToken)) {
            throw new RuntimeException("리프레쉬 토큰이 유효하지 않습니다.");
        }
        // 서버에 저장된 refresh token 동일한지 확인
        String savedRefreshToken = refreshTokenStore.get(userId);
        if (!savedRefreshToken.equals(requestedRefreshToken)) {
            throw new RuntimeException("리프레쉬 토큰이 유효하지 않습니다.");
        }

        // 새로운 access, refresh token 생성
        String newAccessToken = jwtProvider.generateAccessToken(userId);
        String newRefreshToken = jwtProvider.generateRefreshToken(userId);

        // 리프레쉬 토큰 갱신
        refreshTokenStore.put(userId, newRefreshToken);

        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setAccessToken(newAccessToken);
        responseDto.setRefreshToken(newRefreshToken);

        return responseDto;
    }
}
