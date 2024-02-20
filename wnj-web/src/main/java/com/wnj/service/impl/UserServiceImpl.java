package com.wnj.service.impl;

import org.springframework.stereotype.Service;
import com.wnj.common.ServiceException;
import com.wnj.dao.UserDAO;
import com.wnj.domain.UserDO;
import com.wnj.query.QueryUser;
import com.wnj.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther naijiang.wang
 * @date 17-11-28 下午8:54
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDAO userDAO;

    @Override
    public UserDO queryUserByUserId(String userId) throws ServiceException {
        return userDAO.queryUserByUserId(userId);
    }

    @Override
    public UserDO queryUserByLoginName(String loginName) throws ServiceException {
        return userDAO.queryUserByLoginName(loginName);
    }

    @Override
    public List<UserDO> listUser(QueryUser queryUser) throws ServiceException {
        return userDAO.listUser(queryUser);
    }

    @Override
    public UserDO insertUser(UserDO userDO) throws ServiceException {
        userDAO.insertUser(userDO);
        return userDO;
    }

    @Override
    public Integer updateUserById(UserDO userDO) throws ServiceException {
        return null;
    }
}
