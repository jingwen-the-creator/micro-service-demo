package com.example.user.controller;

import com.alibaba.druid.sql.visitor.functions.Bin;
import com.example.param.UserCheckParam;
import com.example.param.UserLoginParam;
import com.example.pojo.User;
import com.example.user.service.UserService;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //用json数据进行交互
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验用户的名称是否可用
     * @param userCheckParam 接受检查的账号用户名，内部有@Validated注解检查
     * @param result 获取校验结果的实体对象
     * @return 封装结果R对象
     *
     *     //@RequestBody 接受前端传递的json数据
     *     // @Validated 令UserCheckParam的@NotBlank注解生效
     *     //BindingResult 只能跟在校验参数后面，检查结果是否成功
     */
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result){
        //检查是否符合注解的规则
        if(result.hasErrors()){
            return R.fail("invalid username!");
        }
        //通过BindingResult审核（UserCheckParam的@NotBlank注解生效）
        //即可调用用户数据接口
        return userService.check(userCheckParam);
    }

    //todo 注册无法接收user的user name
    @PostMapping("register")
    public R register(@RequestBody User user, BindingResult result){
        System.out.println("controller:" + user.getUserName());
        System.out.println("controller:" + user.getUserPhonenumber());
        if(result.hasErrors()){
            return R.fail("参数异常，不可注册。");
        }
        return userService.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam loginParam, BindingResult result){

        return userService.login(loginParam);
    }
}
