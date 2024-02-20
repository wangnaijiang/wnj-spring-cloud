package com.wnj.dto;

import lombok.Data;
import com.wnj.common.BaseRequest;

@Data
public class LoginReq extends BaseRequest {
    private String username;
    private String password;
}
