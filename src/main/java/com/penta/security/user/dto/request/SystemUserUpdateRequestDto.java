package com.penta.security.user.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemUserUpdateRequestDto {

    @NotEmpty(message = "이름은 필수항목입니다.")
    public String userNm;
}
