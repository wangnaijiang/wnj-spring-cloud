package com.wnj;

import org.junit.Test;
import com.wnj.common.Result;
import com.wnj.dto.UserResp;

public class BaseTest {

    @Test
    public void testData(){
        Result result = new Result<>();
        result.setCode(200);
        result.setMsg("success");
        UserResp userResp = new UserResp();
        userResp.setUserId("id-1");
        userResp.setName("lucky");
        result.setData(userResp);
        System.out.printf(result.toString());
    }

}
