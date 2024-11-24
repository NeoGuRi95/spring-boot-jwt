package com.penta.security.auth.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    @NotEmpty(message = "아이디는 필수항목입니다.")
    private String userId;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String userPw;
}
