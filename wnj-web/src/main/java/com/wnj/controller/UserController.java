package com.wnj.controller;

import com.wnj.config.RedissonLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.wnj.common.LocalCache;
import com.wnj.common.Result;
import com.wnj.common.constant.GlobalConstant;
import com.wnj.common.constant.LoggerNameConstant;
import com.wnj.config.cache.CacheProperties;
import com.wnj.config.demo.DemoProperties;
import com.wnj.domain.UserDO;
import com.wnj.dto.LoginResp;
import com.wnj.dto.UserResp;
import com.wnj.dto.LoginReq;
import com.wnj.query.QueryUser;
import com.wnj.service.UserService;
import com.wnj.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@Controller
@CrossOrigin(origins ="*", maxAge = 1000)
@RequestMapping(value = "/user")
@ResponseBody
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(LoggerNameConstant.COMMON_DIGEST);

	@Resource
	private UserService userService;

	@Resource
	private DemoProperties demoProperties;

	@Value("${com.demo.type2}")
	private String propertyType;

	@Resource
	private CacheProperties cacheProperties;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedissonLockService redissonLockService;

	/**
	 * <pre>
	 *     post方式访问 http://localhost:8080/user/login
	 * </pre>
	 */
	@RequestMapping(value = "/login")
	public Result<LoginResp> login(@RequestBody LoginReq loginReq) {
		stringRedisTemplate.opsForValue().set("k", String.valueOf(System.currentTimeMillis()));
		String v = stringRedisTemplate.opsForValue().get("k");
//		String lockKey = "login-"+v;
		String lockKey = "k1";
		redissonLockService.tryLock(lockKey, ()->{
//			doBizInLock(lockKey);
			logger.info("lockKey={},{0}", lockKey);
			return lockKey;
		});
		LoginResp loginResp = new LoginResp();
		if(StringUtil.isBlank(loginReq.getUsername())){
			return Result.buildFail4NoLogin(loginResp);
		}
		UserDO userDO = userService.queryUserByLoginName(loginReq.getUsername());
		if(userDO != null && StringUtil.equals(userDO.getPassword(), loginReq.getPassword())){
			String token = UUIDUtil.genUUID();
			LocalCache.setObject(token, userDO);
//			CookiesUtil.setCookie(response, GlobalConstant.TOKEN, token, DateUtil.SECONDS_OF_HOUR);
			loginResp.setToken(token);
			loginResp.setTimeStamp(Long.valueOf(v));
			loginResp.setLoginSuccess(true);
			return Result.buildSuccess(loginResp);
		}
		return Result.buildFail4NoLogin(loginResp);
	}

	private void doBizInLock(String lockKey){
		redissonLockService.tryLock(lockKey, ()->{
			ThreadUtil.sleep(1000 * 60);
			logger.info("doBizInLock, lockKey={},{0}", lockKey);
			return lockKey;
		});
	}

	@RequestMapping(value = "/logout")
	public Result<Boolean> logout() {
//		LoginResp loginResp = new LoginResp();
//		if(StringUtil.isBlank(loginReq.getUsername())){
//			return Result.buildFail4NoLogin(loginResp);
//		}
//		UserDO userDO = userService.queryUserByLoginName(loginReq.getUsername());
//		if(userDO != null && StringUtil.equals(userDO.getPassword(), loginReq.getPassword())){
//			String token = UUIDUtil.genUUID();
//			LocalCache.setObject(token, userDO);
////			CookiesUtil.setCookie(response, GlobalConstant.TOKEN, token, DateUtil.SECONDS_OF_HOUR);
//			loginResp.setToken(token);
//			loginResp.setLoginSuccess(true);
//			return Result.buildSuccess(loginResp);
//		}
//		return Result.buildFail4NoLogin(loginResp);
		return Result.buildSuccess(Boolean.TRUE);
	}

	/**
	 * <pre>
	 *     http://localhost:8080/user/list
	 * </pre>
	 */
	@RequestMapping(value = "/info")
	public Result<UserResp> info(HttpServletRequest httpServletRequest) {
		UserDO userDO = (UserDO)httpServletRequest.getAttribute(GlobalConstant.USER_SESSION_KEY);
		return Result.buildSuccess(UserConvertor.toUserDTO(userDO));
	}


	/**
	 * <pre>
	 *     http://localhost:8080/user/list
	 * </pre>
	 */
	@RequestMapping(value = "/list")
	public Result<List<UserResp>> list() {
		QueryUser queryUser = new QueryUser();
//		queryUser.setName("t");
		List<UserDO> userDOS = userService.listUser(queryUser);
		List<UserResp> list = new ArrayList<>();
		for (UserDO userDO : userDOS) {
			list.add(UserConvertor.toUserDTO(userDO));
		}
		return Result.buildSuccess(list);
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
