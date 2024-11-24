package com.penta.security.auth.service;

import com.penta.security.auth.vo.CustomUserDetails;
import com.penta.security.user.entity.SystemUser;
import com.penta.security.user.repository.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        SystemUser systemUser = systemUserRepository.findByUserId(userId)
            .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저가 없습니다."));

        return new CustomUserDetails(systemUser);
    }
}