package com.penta.security.auth.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDto {

    private String accessToken;

    private String refreshToken;
}
