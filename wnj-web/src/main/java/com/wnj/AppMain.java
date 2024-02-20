package com.wnj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 入口类: @SpringBootApplication注解的main类, 注意以下几点:
 * <pre>
 *  1,全工程唯一
 *  2,它要和所有类(包括测试类),同属一个根包
 * </pre>
 *
 */
@ServletComponentScan
@SpringBootApplication
//@SpringBootApplication(scanBasePackages="com.wnj.*")
public class AppMain {

	public static void main(String[] args) {
		SpringApplication.run(AppMain.class, args);
	}
}
