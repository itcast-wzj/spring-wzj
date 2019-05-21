package com.mapper;

import com.bean.User;

public interface UserMapper {
	//根据用户名密码登录
	int selectByup(User user)throws Exception;
}
