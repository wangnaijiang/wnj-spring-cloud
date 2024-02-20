package com.wnj.controller;

import com.wnj.dto.LoginReq;
import com.wnj.dto.UserResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.wnj.common.Result;
import com.wnj.common.constant.LoggerNameConstant;
import com.wnj.config.cache.CacheProperties;
import com.wnj.config.demo.DemoProperties;
import com.wnj.domain.UserDO;
import com.wnj.query.QueryUser;
import com.wnj.service.UserService;
import com.wnj.util.DateUtil;
import com.wnj.util.LoggerUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
//@Controller
@CrossOrigin(origins ="*", maxAge = 1000)
@RequestMapping(value = "/test")
@ResponseBody
public class RestController {

	private static Logger logger = LoggerFactory.getLogger(LoggerNameConstant.COMMON_DIGEST);

	@Resource
	private UserService userService;

	@Resource
	private DemoProperties demoProperties;

	@Value("${com.demo.type2}")
	private String propertyType;

	@Resource
	private CacheProperties cacheProperties;

	/**
	 * <pre>
	 *     http://localhost:8080/user/login?username=ok&password=123456
	 * </pre>
	 */
//	@PostMapping(value = "/login")
	@RequestMapping(value = "/login")
	public Result<UserResp> login(@RequestBody LoginReq userRequest) {
		if("111111".equals(userRequest.getPassword())){
            UserResp userResp = new UserResp();
			userResp.setUserId("123");
			userResp.setName("luckyAdmin");
			return Result.buildSuccess(userResp);
		}
		return Result.buildFail4NoLogin(null);
	}

	/**
	 * <pre>
	 *     http://localhost:8080/user
	 * </pre>
	 */
	@RequestMapping
    public String index() {
		Thread thread = Thread.currentThread();
		System.out.println(thread +"===index==="+ thread.getClass().getClassLoader());
		return "hello world";
	}

	/**
	 * <pre>
	 *     http://localhost:8080/user/list
	 * </pre>
	 */
	@RequestMapping(value = "/list")
	public List<UserDO> list() {
		QueryUser queryUser = new QueryUser();
		queryUser.setName("t");
		return userService.listUser(queryUser);
	}

	/**
	 * <pre>
	 *     http://localhost:8080/user/get?name=wangnaijiang //ok
	 * </pre>
	 */
	@RequestMapping(value = "/get")
	public HashMap<String, Object> get(@RequestParam String name) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("title", "hello world");
		map.put("name", name);
		map.put("now", DateUtil.formatTime(new Date()));
//		map.put("demoProperties",demoProperties);
//		map.put("cacheProperties",cacheProperties);

		LoggerUtil.info(logger,"param={0},result={1}",name,map);
		try{
			int a = 0;
			int b = 1/a;
		}catch (Exception e){
			LoggerUtil.error(e, logger,"param={0},result={1}",name,map);
		}

		return map;
	}

	/**
	 * <pre>
	 *     @PathVariable rest占位符
	 *     @PathVariable("username") String name //占位符映射参数名
	 *     http://localhost:8080/user/rest/1/wangnaijiang //ok
	 * </pre>
	 */
	@GetMapping
	@RequestMapping(value = "/rest/{id}/{username}")
	public UserDO getUser(@PathVariable int id, @PathVariable("username") String name) {
		UserDO userDO = new UserDO();
		userDO.setId(id);
		userDO.setName(name);
		userDO.setCreateTime(new Date());
		return userDO;
	}

	/**
	 * <pre>
	 *     http://localhost:8080/user/create?name=ok
	 * </pre>
	 */
	@RequestMapping(value = "/create")
	public UserDO createUser(@RequestParam String name) {
		UserDO userDO = new UserDO();
		userDO.setName(name);
		userDO.setCreateTime(new Date());

		UserDO insertUserDO = userService.insertUser(userDO);
		return insertUserDO;
	}

}
