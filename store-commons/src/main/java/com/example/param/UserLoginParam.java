package com.example.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录的参数（VO作用）
 * 通过用户输入 username和pwd两个参数，获取需要的后台数据
 */
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
