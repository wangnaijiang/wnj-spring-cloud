/**
* @Description UserService is generate by Tools. 
* @author naijiang.wang
* @Time  2017-07-06 11:34:39
*/

package com.wnj.service;

import com.wnj.common.ServiceException;
import com.wnj.domain.UserDO;
import com.wnj.query.QueryUser;

import java.util.List;


public interface UserService {

	UserDO queryUserByUserId(String userId) throws ServiceException;
	UserDO queryUserByLoginName(String loginName) throws ServiceException;
	List<UserDO> listUser(QueryUser queryUser) throws ServiceException;
	UserDO insertUser(UserDO userDO) throws ServiceException;
	Integer updateUserById(UserDO userDO) throws ServiceException;

}



