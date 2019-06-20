package cn.itcast.mapper;

import cn.itcast.po.User;

public interface UserMapper {
	// select * from user where username = ? and password = ?
	User selectByUP(User user);
}
