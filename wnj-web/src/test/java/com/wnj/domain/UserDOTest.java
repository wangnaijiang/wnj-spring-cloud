package com.wnj.domain;

import org.junit.Test;

import java.util.Date;

public class UserDOTest {
    @Test
    public void lombok(){
        UserDO userDO = new UserDO();
        userDO.setId(1);
        userDO.setUserId("202101");
        userDO.setCreateTime(new Date());
        userDO.setName("com/wnj");
        System.out.println(userDO);
    }
}