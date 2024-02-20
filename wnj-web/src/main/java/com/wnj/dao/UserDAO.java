package com.wnj.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.wnj.domain.UserDO;
import com.wnj.mybatis.BizId;
import com.wnj.query.QueryUser;

import java.util.List;

@Mapper
public interface UserDAO {

	UserDO queryUserByUserId(@Param("userId") String userId) ;

	UserDO queryUserByLoginName(@Param("loginName") String loginName) ;

	List< UserDO >listUser(QueryUser queryUser) ;

	@BizId(idName = "userId")
	int insertUser(UserDO UserDO) ;

	int updateUserById(UserDO UserDO) ;

}

