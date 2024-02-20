package com.wnj.dto;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class UserResp {
    private Integer id;
    private String userId;
    private String loginName;
    private String name;
    private String createTime;
    private String modifyTime;

    private List<String> roles = Lists.newArrayList("admin");
    private String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
    private String introduction = "I am administrator";

}
