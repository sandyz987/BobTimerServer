package com.sandyz.bobtimerserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenVo {
    private String token;
    private Long expireTime;
}
