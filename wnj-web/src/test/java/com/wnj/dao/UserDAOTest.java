package com.wnj.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import com.wnj.TestAppMain;
import com.wnj.domain.UserDO;
import com.wnj.query.QueryUser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestAppMain.class)
public class UserDAOTest {

    @Resource
    private UserDAO userDAO;

    @Test
    public void insertUser() {
        UserDO userDO = new UserDO();
        userDO.setName("com/wnj");
        userDO.setCreateTime(new Date());
        userDAO.insertUser(userDO);
        System.out.println(userDO);
    }

    @Test
    public void listUser() {
        QueryUser queryUser = new QueryUser();
        queryUser.setName("t");
        List<UserDO> userDOList = userDAO.listUser(queryUser);
        for (UserDO userDO : userDOList) {
            System.out.println(userDO);
        }
    }

}
