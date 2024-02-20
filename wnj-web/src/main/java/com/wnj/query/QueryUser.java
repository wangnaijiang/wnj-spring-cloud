/**
* UserDO entity encapsulation table BbsTopic record.
* @author Eclipse Tools Generate.
* @Time 2017-07-06 11:34:39
* Copyright by LuYuanliang.
*/

package com.wnj.query;

import lombok.Data;

import java.util.Date;

@Data
public class QueryUser {

	private Integer id;
	private String userId;
	private String loginName;
	private String password;
	private String name;
	private Date createTime;
}


