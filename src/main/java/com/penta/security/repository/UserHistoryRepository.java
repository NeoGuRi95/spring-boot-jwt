package com.penta.security.repository;

import com.penta.security.entity.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Integer> {
}

