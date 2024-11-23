package com.penta.security.dto.response;

import com.penta.security.entity.SystemUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemUserResponseDto {

    private Integer userIdx;

    private String userId;

    private String userPw;

    private String userNm;

    private String userAuth;

    public SystemUserResponseDto(SystemUser systemUser) {
        this.userIdx = systemUser.getUserIdx();
        this.userId = systemUser.getUserId();
        this.userPw = systemUser.getUserPw();
        this.userNm = systemUser.getUserNm();
        this.userAuth = systemUser.getUserAuth();
    }
}
