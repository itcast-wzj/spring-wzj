package com.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//对应xxx.xml,指明他是一个配置类
@Configuration
public class BeansConfig {
	
	/**
	 *     相当于xml中的bean标签
	 * @bean注解不指定name属性值,默认就为方法名
	 *     将方法的返回值注入 ioc容器中
	 * @return
	 */
	@Bean
	public User getUser() {
		User user = new User();
		user.setName("wzj");
		user.setCar(new Car("宝马"));
		return user;
	}
}
