package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
        自定义一个异常 
 * @author 王志坚
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="用户名和密码不匹配")
public class UserNameNotMatchPasswordException extends RuntimeException{

}
