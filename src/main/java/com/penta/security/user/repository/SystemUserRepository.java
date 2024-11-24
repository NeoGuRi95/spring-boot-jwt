package com.penta.security.user.repository;

import com.penta.security.user.entity.SystemUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUser, Integer> {
    Optional<SystemUser> findByUserId(String userId);

    List<SystemUser> findByUserIdContainingOrUserNmContaining(String userId, String userNm);

    void deleteByUserId(String userId);
}

