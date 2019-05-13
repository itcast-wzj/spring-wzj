package com.java;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 	相当于xml配置方式中,在applicationContext.xml中模块化配置
 *  <import resource="xxx/xxx.xml"/>
 *  @author 王志坚
 *
 */
@Configuration
@Import(value = {BeansConfig.class})
public class AopConfig {

}
