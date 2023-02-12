package com.example.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.User;

//继承mybatis-plus的basemapper，减少书写单表操作
public interface UserMapper extends BaseMapper<User> {
}
