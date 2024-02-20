package com.wnj.dto;

import lombok.Data;

@Data
public class LoginResp {
    private boolean loginSuccess;
    private String token;
    private long timeStamp;
}
