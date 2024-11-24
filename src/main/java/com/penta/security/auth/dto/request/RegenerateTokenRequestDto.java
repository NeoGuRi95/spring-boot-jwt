package com.penta.security.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegenerateTokenRequestDto {

    private String userId;

    private String refreshToken;
}
