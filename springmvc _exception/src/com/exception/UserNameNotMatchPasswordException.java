package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
        �Զ���һ���쳣 
 * @author ��־��
 *
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN,reason="�û��������벻ƥ��")
public class UserNameNotMatchPasswordException extends RuntimeException{

}
