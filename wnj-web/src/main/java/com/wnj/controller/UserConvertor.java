package com.wnj.controller;

import org.springframework.beans.BeanUtils;
import com.wnj.domain.UserDO;
import com.wnj.dto.UserResp;
import com.wnj.util.DateUtil;

public class UserConvertor {
    public static UserResp toUserDTO(UserDO userDO){
        if(userDO == null){
            return null;
        }
        UserResp userResp = new UserResp();
        BeanUtils.copyProperties(userDO, userResp);
        userResp.setCreateTime(DateUtil.formatDate(userDO.getCreateTime()));
        userResp.setModifyTime(DateUtil.formatDate(userDO.getCreateTime()));


        return userResp;
    }

}
