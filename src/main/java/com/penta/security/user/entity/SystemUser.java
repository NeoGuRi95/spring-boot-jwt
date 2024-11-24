package com.penta.security.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "SYSTEM_USER")
@Data
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 기본키")
    private Integer userIdx;

    @Column(nullable = false, unique = true, length = 30)
    @Comment("회원 아이디")
    private String userId;

    @Column(nullable = false, length = 100)
    @Comment("회원 비밀번호")
    private String userPw;

    @Column(nullable = false, length = 100)
    @Comment("회원 이름")
    private String userNm;

    @Column(nullable = false, length = 20)
    @Comment("회원 권한")
    private String userAuth;
}
