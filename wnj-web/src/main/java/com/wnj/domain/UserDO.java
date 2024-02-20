/**
* UserDO entity encapsulation table BbsTopic record.
* @author Eclipse Tools Generate.
* @Time 2017-07-06 11:34:39
* Copyright by LuYuanliang.
*/

package com.wnj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserDO {

	private Integer id;
	private String userId;
	private String loginName;
	private String password;
	private String name;
	private Date createTime;

}


