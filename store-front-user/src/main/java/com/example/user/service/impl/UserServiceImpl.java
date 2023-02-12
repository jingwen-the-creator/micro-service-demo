package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.param.UserCheckParam;
import com.example.pojo.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //加入IoC容器
@Slf4j //日志输出
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public R check(UserCheckParam userCheckParam) {
        // 1. 参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userCheckParam.getUserName());

        // 2. 数据库查询
        Long total = userMapper.selectCount(queryWrapper);
        // 3.查询结果处理
        if(total == 0){
            log.info("UserServiceImpl.check()业务结束，结果是{}","账号可以使用！");
            return R.ok("账户可以使用");
        }
        log.info("UserServiceImpl.check()业务结束，结果是{}","账号已被占用。");
        return R.fail("账号已被占用");
    }
}
