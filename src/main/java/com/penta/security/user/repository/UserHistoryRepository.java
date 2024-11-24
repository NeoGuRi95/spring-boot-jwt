package com.penta.security.user.repository;

import com.penta.security.user.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {
}

