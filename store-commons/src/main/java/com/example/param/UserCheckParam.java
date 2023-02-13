package com.example.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 属于User
 * 通过username单个参数，查询
 * 在检查参数只有name的时候代替User类使用 （VO作用）
 */
@Data
public class UserCheckParam {

    @NotBlank
    private String userName; // 等于前段传递的json key名称
}
