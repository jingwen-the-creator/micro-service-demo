package com.example.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.constants.UserConstants;
import com.example.param.UserCheckParam;
import com.example.param.UserLoginParam;
import com.example.pojo.User;
import com.example.user.mapper.UserMapper;
import com.example.user.service.UserService;
import com.example.utils.MD5Util;
import com.example.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service //加入IoC容器
@Slf4j //日志输出
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *
     * @param userCheckParam 账号参数 已经校验完毕
     * @return 校验结果
     */
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

    /**
     *  用户注册： 封装、检查账号是否存在、加密、插入数据、查询是否插入成功、返回结果
     * @param user 前台传来的用户对象
     * @return 返回插入结果
     */
    @Override
    public R register(User user) {
        // 0. 参数封装
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        Long total = userMapper.selectCount(queryWrapper);
        // 1. 检查账号是否存在
        if(total > 0){
            log.info("UserServiceImpl.register()业务结束，结果是{}","账号已经被占用！");
            return R.fail("账号已经被占用！");
        }
        // 2. 密码加密
        String encode = MD5Util.encode(user.getPassword() + UserConstants.USER_SALT);
        user.setPassword(encode);

        System.out.println("!!!!====!!"+user.getUserName()+user.getPassword());
        // 3. 插入数据
        int rows = userMapper.insert(user);
        // 4. 数据是否插入成功 （并发）
        if(rows == 0){
            log.info("UserServiceImpl.register()业务结束，结果是{}", "注册失败，请稍后再试。");
            return R.fail("注册失败，请稍后再试。");
        }
        // 5. 返回封装结果
        log.info("UserServiceImpl.register()业务结束，结果是{}", "注册成功。");
        return R.ok("注册成功。");
    }

    /**
     * 登录业务
     * 1 密码加密
     * 2 通过账号 密码进行数据库查询 返回user对象
     * 3 返回结果（成功/失败字符串 + data 包括用户id和名称 ）
     * @param loginParam 账号和密码，但是给的密码是decoded，需要加密之后对比
     * @return
     */
    @Override
    public R login(UserLoginParam loginParam) {

//        System.out.println(loginParam.getPassword() + "," + loginParam.getUserName());
        String encode = MD5Util.encode(loginParam.getPassword() + UserConstants.USER_SALT);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginParam.getUserName());
        queryWrapper.eq("password", encode);

        User user = userMapper.selectOne(queryWrapper);

        if (user != null) {
            log.info("UserServiceImpl.login()业务结束，结果是{}", "登录成功。");
            // password不可返回 @JsonInclude(JsonInclude.Include.NON_NULL)
            user.setPassword(null);
            return R.ok("登录成功 001", user);

        }
        log.info("UserServiceImpl.login()业务结束，结果是{}", "登录失败，账号或者密码错误。");
        return R.fail("账号或者密码错误！");
    }
}
